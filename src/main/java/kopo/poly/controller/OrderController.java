package kopo.poly.controller;


import com.amazonaws.services.glue.model.Order;
import com.fasterxml.jackson.databind.ObjectReader;
import com.sun.org.apache.xpath.internal.operations.Or;
import kopo.poly.dto.OrderDTO;
import kopo.poly.dto.ProductDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IOrderService;
import kopo.poly.service.IProductService;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class OrderController {


    @Resource(name = "OrderService")
    private IOrderService OrderService;

    @Resource(name = "ProductService")
    private IProductService ProductService;

    @Resource(name = "UserService")
    private IUserService UserService;

    @RequestMapping(value = "/orderlogic")
    public String orderlogic(HttpSession session,ModelMap model, HttpServletRequest request)
        throws Exception{

        log.info(this.getClass().getName() + "오더 컨트롤러 오더 로직 시작!");

        int res = 0;
        int res2 = 0;
        int res3 = 0;
        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";

        String ord_id = (String) session.getAttribute("SS_USER_ID");

        String product_seq = CmmUtil.nvl(request.getParameter("product_seq"));

        log.info("유저아이디는??" + ord_id);
        log.info("jsp에서 가져온 상품 seq??" + product_seq);

        OrderDTO oDTO = null;
        OrderDTO pDTO = null;
        OrderDTO tDTO = null;
        oDTO = new OrderDTO();
        pDTO = new OrderDTO();
        oDTO.setOrd_id(ord_id);
        oDTO.setProduct_seq(product_seq);

        res = OrderService.searchorderinfo(oDTO);

        if(res == 1){
            log.info("디비결과가 아무것도 없음");
            res2 = OrderService.InsertOrder(oDTO);
            if(res2 == 1){
               
                log.info("오더테이블에 정보 넣기 성공");
                msg = "주문성공~!";
                url = "/userpage";
                icon = "success";
                contents = "고객님의 주문이 성공하였습니다";

            }else {
                log.info("오더테이블에 정보 넣기 실패");
                msg = "주문실패";
                url = "/index";
                icon = "warning";
                contents = "고객님의 주문이 실패하였습니다";
            }
        }else if(res == 2){
            log.info("디비결과 있음");
            pDTO = OrderService.select(oDTO);
            log.info("pDTO" + pDTO);

            int ord_seq = pDTO.getOrd_seq();
            String pro_seq = pDTO.getProduct_seq();

            log.info("ord_Seq값은?" + ord_seq);
            log.info("pro_seq rkqtdms?" + pro_seq);

            String pro_seq_fi = pro_seq + "," + product_seq;

            log.info("pro_seq_fi의 값은?" + pro_seq_fi);

            OrderDTO uDTO = null;

            uDTO = new OrderDTO();

            uDTO.setPro_seq(pro_seq_fi);
            uDTO.setOrd_seq(ord_seq);

            res3 = OrderService.updateOrder(uDTO);

            if(res3 == 1){

                log.info("오더테이블에 다중정보 넣기 성공");
                msg = "주문성공~!";
                url = "/userpage";
                icon = "success";
                contents = "고객님의 주문이 성공하였습니다";

            }else {
                log.info("오더테이블에 다중정보 넣기 실패");
                msg = "주문실패";
                url = "/index";
                icon = "warning";
                contents = "고객님의 주문이 실패하였습니다";
            }



        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        model.addAttribute("icon",icon);
        model.addAttribute("contents", contents);
        return "/alert/resultalert";
    }


    @RequestMapping(value = "/userpage")
    public String userpage(HttpSession session,ModelMap model, HttpServletRequest request) throws Exception{

        log.info(this.getClass().getName() + "오더 컨트롤러 오더 유저페이지 시작!");

        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";
        String ord_id = (String) session.getAttribute("SS_USER_ID");

        if(session.getAttribute("SS_USER_ID") == null){
            log.info("user_id 값은?? " + ord_id);
            msg = "구매자로 로그인 해주세요";
            url = "/loginPage";
            icon = "warning";
            contents = "구매자 아이디로 로그인이 필요한 서비스입니다";

            model.addAttribute("msg", msg);
            model.addAttribute("url",url);
            model.addAttribute("icon",icon);
            model.addAttribute("contents",contents);
            return "/alert/resultalert";
        }

        OrderDTO pDTO = null;
        OrderDTO qDTO = null;
        OrderDTO wDTO = null;
        qDTO = new OrderDTO();
        wDTO = new OrderDTO();
        ArrayList seq_list = new ArrayList<>();
        ArrayList title_list = new ArrayList<>();
        ArrayList price_list = new ArrayList<>();
        ArrayList type_list = new ArrayList<>();
        ArrayList sellid_list = new ArrayList<>();



        log.info("유저아이디는??" + ord_id);

        qDTO.setOrd_id(ord_id);
        wDTO = OrderService.selectpro_seq(qDTO);
        //상품 seq가 널이아니면
        if(wDTO.getProduct_seq() != null){

            String product_seq = CmmUtil.nvl(wDTO.getProduct_seq());

            log.info("jsp에서 가져온 상품 seq??" + product_seq);

            OrderDTO oDTO = null;
            oDTO = new OrderDTO();
            pDTO = new OrderDTO();


            String multiorder = product_seq;
            int a = multiorder.length();
            String[] arr = multiorder.split(",", a);


            for (int i = 0; i < arr.length; i++) {


                oDTO = null;
                oDTO = new OrderDTO();
                oDTO.setOrd_id(ord_id);
                oDTO.setProduct_seq(arr[i]);
                System.out.println(arr[i]);
                pDTO = OrderService.getproductinfo(oDTO);


                if(pDTO == null){
                    pDTO = new OrderDTO();
                    log.info("DB 조회 결과 널값이 넘어와서 강제로 메모리에 올림");
                }else {
                    seq_list.add(CmmUtil.nvl(pDTO.getPro_seq()));
                    title_list.add(CmmUtil.nvl(pDTO.getPro_title()));
                    price_list.add(CmmUtil.nvl(pDTO.getPro_price()));
                    type_list.add(CmmUtil.nvl(pDTO.getPro_type()));
                    sellid_list.add(CmmUtil.nvl(pDTO.getPro_reg_id()));
                }
            }
            for (int i = 0; i < arr.length; i++){
                System.out.println(seq_list.get(i));
                System.out.println(title_list.get(i));
                System.out.println(price_list.get(i));
                System.out.println(type_list.get(i));
                System.out.println(sellid_list.get(i));

            }
        }else {
            log.info("노답 노답");
            wDTO = new OrderDTO();
        }

        model.addAttribute("seq_list", seq_list);
        model.addAttribute("title_list", title_list);
        model.addAttribute("price_list", price_list);
        model.addAttribute("type_list", type_list);
        model.addAttribute("sellid_list", sellid_list);
        model.addAttribute("msg",msg);
        model.addAttribute("url",url);
        model.addAttribute("icon",icon);



        return "/mypage/Userpage";
    }

    @GetMapping(value = "ord_delete")
    public String ord_delete(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "오더 삭제 시작");

        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";
        String pro_seq = CmmUtil.nvl(request.getParameter(("pro_seq")));
        try {


            log.info("ord_seq 값은?? " + pro_seq);

            ProductDTO pDTO = new ProductDTO();

            pDTO.setPro_seq(Integer.parseInt(pro_seq));

            OrderService.deleteorder(pDTO);

            msg = "삭제되었습니다";
            icon = "success";
            contents = "등록하신 상품 정보가 삭제되었습니다";
            url = "/userpage";

        } catch (Exception e) {
            msg = "실패하였습니다" + e.getMessage();
            log.info(e.toString());
        } finally {
            log.info(this.getClass().getName() + "판매 상품 삭제 끝");

            model.addAttribute("msg", msg);
            model.addAttribute("icon", icon);
            model.addAttribute("contents", contents);
            model.addAttribute("url", url);
        }

        return "/alert/resultalert";
    }

    @PostMapping(value = "test")
    @ResponseBody
    public int test(HttpServletRequest request, HttpSession session, @RequestParam Map<String, Object> map)throws Exception{

        int prod_seq1 = Integer.parseInt(String.valueOf(map.get("prod_seq")));

        List<Integer> cart_list = (List<Integer>) session.getAttribute("SS_CART_LIST");

        cart_list.add(prod_seq1);

        session.setAttribute("SS_CART_LIST", cart_list);

        log.info("들어온 상품이 추가된 장바구니 : " + cart_list);

        return prod_seq1;
    }



}
