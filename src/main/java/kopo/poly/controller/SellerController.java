package kopo.poly.controller;

import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.ProductDTO;
import kopo.poly.dto.SellerInfoDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IProductService;
import kopo.poly.service.ISellerService;
import kopo.poly.service.IUserService;
import kopo.poly.service.impl.ProductService;
import kopo.poly.service.impl.SellerService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class SellerController {

    @Resource(name = "SellerService")
    private ISellerService SellerService;

    @Resource(name = "ProductService")
    private IProductService ProductService;

    //로그인 페이지
    @GetMapping(value = "/sellloginPage")
    public String selllogin() throws Exception {


        return "/login/sellerlogin";

    }


    //로그인페이지 로직
    @PostMapping(value = "/sellloginPage1")
    public String sellloginPage(HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + "SellerController : 로그인페이지 시작");

        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";
        int res = 0;

        SellerInfoDTO pDTO = null;


        try {
            String sell_id = CmmUtil.nvl(request.getParameter("sell_id"));
            String sell_pw = CmmUtil.nvl(request.getParameter("sell_pw"));

            log.info("sell_id : " + sell_id);
            log.info("sell_pw : " + sell_pw);

            pDTO = new SellerInfoDTO();
            pDTO.setSell_id(sell_id);
            pDTO.setSell_pw(EncryptUtil.encHashSHA256(sell_pw));

            res = SellerService.SellerLoginCheck(pDTO);


            if (res == 1) {
                msg = "로그인 성공";
                url = "/index";
                icon = "success";
                contents = "판매자 로그인에 성공하셧습니다";
                session.setAttribute("SS_SELL_ID", sell_id);
                log.info("sell_id : " + sell_id);

            } else {
                msg = "로그인 실패!";
                url = "/sellloginPage";
                icon = "warning";
            }
        } catch (Exception e) {
            res = 2;
            log.info(e.toString());
            e.printStackTrace();

        } finally {

            log.info(this.getClass().getName() + "SellerController : 로그인페이지 끝!");
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
            model.addAttribute("icon", icon);
            model.addAttribute("contents", contents);

            pDTO = null;

        }
        return "/alert/resultalert";
    }

    @GetMapping(value = "/selllogout") // 로그아웃
    public String userLogout(HttpServletRequest request, ModelMap model) {
        log.info(this.getClass().getName() + ".sell/sellLogout start");
        HttpSession session = request.getSession();

        String url = "/index";
        String msg = "로그아웃 성공";

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        return "/index";
    }

    @GetMapping(value = "/Sellpage")
    public String Sellpage(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws Exception {

        log.info(this.getClass().getName() + "controller : 판매자페이지 시작");

        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";

        String sell_id = (String) session.getAttribute("SS_SELL_ID");
        String user_id = (String) session.getAttribute("SS_USER_ID");

        if (session.getAttribute("SS_SELL_ID") == null) {
            log.info("sell_id의 값은?? " + sell_id);
            msg = "판매자로 로그인 해주세요";
            url = "/loginPage";
            icon = "warning";
            contents = "판매자 아이디로 로그인이 필요한 서비스입니다";

            model.addAttribute("msg", msg);
            model.addAttribute("url",url);
            model.addAttribute("icon",icon);
            model.addAttribute("contents",contents);
            return "/alert/resultalert";
        }

        ProductDTO pDTO = new ProductDTO();
        pDTO.setPro_reg_id(sell_id);

        List<ProductDTO> rList = ProductService.getProductListByReg_id(pDTO);


        if (rList == null) {
            rList = new ArrayList<ProductDTO>();

        }

        log.info("rList가 제대로 오나요?" + rList);
        //리스트 결과값 넣어주기
        model.addAttribute("rList", rList);
        model.addAttribute("url", url);


        rList = null;


        log.info(this.getClass().getName() + "controller : 판매자페이지 끝");

        return "mypage/Sellpage";

    }

    //파일 등록페이지
    @GetMapping(value = "/sellwrite")
    public String selltwrite() throws Exception {

        log.info(this.getClass().getName() + "ProductController : 상품정보페이지 시작");

        log.info(this.getClass().getName() + "ProductController : 상품정보페이지 끝");

        return "/Product/Productenter";

    }

    @ResponseBody
    @PostMapping(value = "/productwrite1")
    public int ProductInsert(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                             ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "MainController : 상품이미지 등록로직 시작");
        String msg = "";
        String url = "";
        String icon = "";

        SellerInfoDTO uDTO = null;
        ProductDTO nDTO = null;
        int res = 0;

        try {
            uDTO = new SellerInfoDTO();
            uDTO = (SellerInfoDTO) session.getAttribute("oDTO");

            String pro_title = CmmUtil.nvl(request.getParameter("pro_title"));
            String pro_reg_id = CmmUtil.nvl(uDTO.getSell_id());
            String pro_content = CmmUtil.nvl(request.getParameter("pro_content"));
            String pro_price = CmmUtil.nvl(request.getParameter("pro_price"));
            String pro_type = CmmUtil.nvl(request.getParameter("pro_type"));

            nDTO = new ProductDTO();
            nDTO.setPro_title(pro_title);
            nDTO.setPro_reg_id(pro_reg_id);
            nDTO.setPro_content(pro_content);
            nDTO.setPro_price(pro_price);
            nDTO.setPro_type(pro_type);

            if (res == 1) {
                log.info("상품 넣기 성공");
            } else if (res == 2) {
                log.info("상품 넣기 실패");

            } else {
                log.info("오류로 상품 넣기 실패!");
            }

        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info(this.getClass().getName() + "MainController : 상품이미지 등록로직 끝");

        }
        return res;
    }

    //회원가입페이지
    @GetMapping(value = "/regSell")
    public String regSell() throws Exception {
        log.info("회원가입 페이지 시작!");

        log.info("회원가입 페이지 끝");

        return "/register/seller";

    }


    //회원가입 로직 시작
    @PostMapping(value = "/regSell1")
    public String insertSell(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "SellerController : 회원가입 시작");


        // 회원가입 결과에 대한 메시지 전달할 변수
        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";
        //웹 회원가입결과에 대한 메시지를 전달할변수
        SellerInfoDTO pDTO = null;

        try {

            String sell_id = CmmUtil.nvl(request.getParameter("sell_id")); //회원아이디
            String sell_pw = CmmUtil.nvl(request.getParameter("sell_pw")); // 비밀번호
            String sell_name = CmmUtil.nvl(request.getParameter("sell_name"));  //회원이름
            String sell_nick = CmmUtil.nvl(request.getParameter("sell_nick"));  //회원닉
            String email = CmmUtil.nvl(request.getParameter("sell_email")); //이메일
            String addr = CmmUtil.nvl(request.getParameter("addr1")); //주소


            log.info("sell_id :" + sell_id);
            log.info("sell_pw :" + sell_pw);
            log.info("sell_name :" + sell_name);
            log.info("sell_nick :" + sell_nick);
            log.info("sell_email :" + email);
            log.info("user_addr :" + addr);

            //유저 정보를 담기위함
            pDTO = new SellerInfoDTO();
            pDTO.setSell_id(sell_id);
            pDTO.setSell_pw(EncryptUtil.encHashSHA256(sell_pw)); // 비밀번호 해시 알고리즘 암호화
            pDTO.setSell_name(sell_name);
            pDTO.setSell_nick(sell_nick);
            pDTO.setSell_email(EncryptUtil.encAES128CBC(email)); // 이메일 AES-128-CBC 암호화
            pDTO.setSell_address(addr);

            int res = SellerService.insertSeller(pDTO);


            if (res == 1) {
                msg = "회원가입이 되었습니다.";
                url = "/sellloginPage";
                icon = "success";
                contents = "회원가입을 축하드립니다";
            } else if (res == 2) {
                msg = "이미 가입된 ID입니다";
                url = "/regSell";
                icon = "warning";
                contents = "이미 가입된 ID입니다";
            } else {
                msg = "오류로 인해 회원가입이 실패했습니다.";
                url = "/regSell";
                icon = "warning";
                System.out.println("오류로 회원가입이 실패했습니다");
            }

        } catch (Exception e) {
            // 저장이 실패되면 유저 보여줄 메시지
            msg = "회원가입에 실패하였습니다.";
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info(this.getClass().getName() + "SellerController : 회원가입 끝");

            // 회원가입 여부 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);
            model.addAttribute("icon", icon);
            model.addAttribute("contents", contents);

            pDTO = null;
        }
        return "/alert/resultalert";
    }

    @GetMapping(value = "pro_delete")
    public String pro_delete(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "판매 상품 삭제 시작");

        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";
        String pro_seq = CmmUtil.nvl(request.getParameter(("pro_seq")));
        try {


            log.info("pro_seq의 값은?? " + pro_seq);

            ProductDTO pDTO = new ProductDTO();

            pDTO.setPro_seq(Integer.parseInt(pro_seq));

            ProductService.deleteProductInfo(pDTO);

            msg = "삭제되었습니다";
            icon = "success";
            contents = "등록하신 상품 정보가 삭제되었습니다";
            url = "/Sellpage";

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

    @GetMapping(value = "pro_edit")
    public String pro_edit(HttpServletRequest request, ModelMap model) {

        log.info(this.getClass().getName() + "  pro_edit 페이지 시작");

        String msg = "";

        String Pro_seq = CmmUtil.nvl(request.getParameter("pro_seq"));

        ProductDTO pDTO = new ProductDTO();

        pDTO.setPro_seq(Integer.parseInt(Pro_seq));

        log.info("Pro_seq 값은??" + Pro_seq);

        model.addAttribute("pDTO", pDTO);

        log.info(this.getClass().getName() + "pro_edit 페이지 끝");

        return "/Product/Producteditinfo";
    }

    //상품게시글 업데이트
    @GetMapping(value = "pro_update")
    public String pro_update(HttpServletRequest request, ModelMap model, HttpSession session) {

        log.info(this.getClass().getName() + "pro_update 시작");

        String msg = "";
        String icon = "";
        String url = "";

        try {

            String pro_reg_id = CmmUtil.nvl((String) session.getAttribute("SS_SELL_ID")); // 아이디
            String pro_title = CmmUtil.nvl(request.getParameter("pro_title")); // 제목
            String pro_content = CmmUtil.nvl(request.getParameter("pro_content")); // 내용
            String pro_price = CmmUtil.nvl(request.getParameter("pro_price"));

            log.info("pro_reg_id : " + pro_reg_id);
            log.info("pro_title : " + pro_title);
            log.info("pro_content : " + pro_content);
            log.info("pro_price" + pro_price);

            ProductDTO rDTO = new ProductDTO();

            rDTO.setPro_reg_id(pro_reg_id);
            rDTO.setPro_title(pro_title);
            rDTO.setPro_content(pro_content);
            rDTO.setPro_content(pro_price);

            // 게시글 수정하기 DB
            ProductService.updatePro(rDTO);

            msg = "수정되었습니다.";
            icon = "success";
            url = "/Sellpage";

        } catch (Exception e) {
            msg = "실패하였습니다. : " + e.getMessage();
            log.info(e.toString());
            e.printStackTrace();

        } finally {
            log.info(this.getClass().getName() + "pro_update 끝!");

            // 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("icon", icon);
            model.addAttribute("url", url);


            return "/alert/resultalert";
        }
    }
}
