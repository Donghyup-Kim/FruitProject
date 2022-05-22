package kopo.poly.controller;

import kopo.poly.dto.SellerInfoDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.ISellerService;
import kopo.poly.service.IUserService;
import kopo.poly.service.impl.SellerService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
public class SellerController {

    @Resource(name = "SellerService")
    private ISellerService sellerService;

    @GetMapping(value = "/sellerloginPage")
    public String selllogin() throws Exception{

        log.info(this.getClass().getName() + "SellerController 판매자로그인페이지 시작");

        log.info(this.getClass().getName() + "SellerController 판매자로그인페이지 끝");

        return "/login/sellerlogin";
    }
    //로그인 로직
    @GetMapping(value = "/sellerloginPage1")
    public String sellerloginPage(HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + "sellercontroller : 판매자로그인페이지 시작");

        String msg = "";
        String url = "";

        int res = 0;

        SellerInfoDTO pDTO = null; // 웹에서 받은 정보를 저장
        SellerInfoDTO rDTO = null; // DB에서 받아온 정보를 저장하여 jsp 넘김
        try {
            String sell_id = CmmUtil.nvl(request.getParameter("sell_id"));
            String sell_pw = CmmUtil.nvl(request.getParameter("sell_pw"));

            log.info("sell_id : " + sell_id);
            log.info("sell_pw : " + sell_pw);

            pDTO = new SellerInfoDTO();
            pDTO.setSell_id(sell_id);
            pDTO.setSell_pw(EncryptUtil.encHashSHA256(sell_pw));

            rDTO = sellerService.SellerLoginCheck(pDTO);

            if (CmmUtil.nvl(rDTO.getSell_id()).length() > 0) {

                msg = rDTO.getSell_id() + "님 로그인 성공!";
                url = "/index";
                session.setAttribute("user_id", rDTO.getSell_id());
                session.setAttribute("user_pw", rDTO.getSell_pw());


            } else {
                url = "/login/sellerlogin";
                msg = "ID.PASSWORD 재확인 부탁드립니다.";
            }
        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
        } finally {

            log.info(this.getClass().getName() + "SellerController : 판매자로그인페이지 끝!");
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

            return "/index";
        }
    }

    //회원가입페이지
    @GetMapping(value = "/regSeller")
    public  String regUser() throws Exception{

        log.info(this.getClass().getName() + "sellercontrolloer : 회원가입 페이지 시작");

        log.info(this.getClass().getName() + "sellercontroller : 회원가입 페이지 끝");

        return "/register/seller";

    }



    //회원가입 페이지 로직
    @GetMapping(value = "/regSeller1")
    public String insertUser(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "SellerController : 회원가입 시작");


        // 회원가입 결과에 대한 메시지 전달할 변수
        String msg = "";
        String url = "";

        //웹 회원가입결과에 대한 메시지를 전달할변수
        SellerInfoDTO pDTO = null;

        try {

            String sell_id = CmmUtil.nvl(request.getParameter("sell_id")); //회원아이디
            String sell_pw = CmmUtil.nvl(request.getParameter("sell_pw")); // 비밀번호
            String sell_name = CmmUtil.nvl(request.getParameter("sell_name"));  //회원이름
            String sell_nick = CmmUtil.nvl(request.getParameter("sell_nick"));  //회원이름
            String email = CmmUtil.nvl(request.getParameter("sell_email")); //이메일
            String sell_address = CmmUtil.nvl(request.getParameter("sell_address")); //주소


            log.info("sell_id :" + sell_id);
            log.info("sell_pw :" + sell_pw);
            log.info("sell_name :" + sell_name);
            log.info("sell_nick :" + sell_nick);
            log.info("sell_email :" + email);
            log.info("sell_address :" + sell_address);

            //유저 정보를 담기위함
            pDTO = new SellerInfoDTO();
            pDTO.setSell_id(sell_id);
            pDTO.setSell_pw(EncryptUtil.encHashSHA256(sell_pw)); // 비밀번호 해시 알고리즘 암호화
            pDTO.setSell_name(sell_name);
            pDTO.setSell_nick(sell_nick);
            pDTO.setSell_email(EncryptUtil.encAES128CBC(email)); // 이메일 AES-128-CBC 암호화
            pDTO.setSell_address(sell_address);

            int res = sellerService.insertSeller(pDTO);

            log.info("res : " + res);


            if (res == 1) {
                msg = "회원가입이 되었습니다.";
                url = "/login/sellerlogin";
            } else if(res == 2) {
                msg = "이미 가입된 ID입니다";
                url = "/register/seller";
            }else {
                msg = "오류로 인해 회원가입이 실패했습니다.";
                url = "/register/seller";
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

            pDTO = null;
        }
        return "/login/sellerlogin";
    }
}
