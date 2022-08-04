package kopo.poly.controller;

import kopo.poly.dto.MailDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IMailService;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import kopo.poly.util.RandomStringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Resource(name = "UserService")
    private IUserService UserService;

    @Resource(name = "MailService")
    private IMailService MailService;

    //로그인 페이지
    @GetMapping(value = "/loginPage")
    public  String userlogin() throws Exception{


        return "/login/userlogin";

    }


    //로그인페이지 로직
    @PostMapping(value = "/loginPage1")
    public  String loginPage(HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + "UserController : 로그인페이지 시작");

        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";
        int res = 0;

        UserInfoDTO tDTO = null;


        try {
            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));

            log.info("user_id : " + user_id);
            log.info("user_pw : " + user_pw);

            tDTO = new UserInfoDTO();
            tDTO.setUser_id(user_id);
            tDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

            res = UserService.getUserLoginCheck(tDTO);
            log.info("res : " + res);
            session.setAttribute("SS_USER_ID", user_id);
            List<Integer> cart_list = new ArrayList<>();
            session.setAttribute("SS_CART_LIST", cart_list);

            if (res == 1) {
                msg = "로그인 성공";
                url = "/index";
                icon = "success";
                contents = "구매자 로그인에 성공하셧습니다";
                log.info("user_id : " + user_id);

            }else {
                msg = "로그인 실패!";
                url = "/loginPage";
                icon = "warning";
            }
        } catch (Exception e) {
            res = 2;
            log.info(e.toString());
            e.printStackTrace();

        } finally {

                log.info(this.getClass().getName() + "UserController : 로그인페이지 끝!");
                model.addAttribute("msg", msg);
                model.addAttribute("url", url);
                model.addAttribute("icon", icon);
                model.addAttribute("contents", contents);

                tDTO = null;

            }
        return  "/alert/resultalert";
        }

    @GetMapping(value = "/userlogout") // 로그아웃
    public String userLogout(HttpServletRequest request, ModelMap model, HttpSession session) {
        log.info(this.getClass().getName() + ".user/userLogout start");

        String msg = "로그아웃 되었습니다";
        String url = "/loginPage";
        String icon = "success";
        String contents = "정상적으로 로그아웃 되었습니다.";

        session.removeAttribute("msg");
        session.invalidate();

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        model.addAttribute("icon", icon);
        model.addAttribute("contents", contents);

        return "/alert/resultalert";
    }



    //회원가입페이지
    @GetMapping(value = "/regUser")
    public  String regUser() throws Exception{



        return "/register/user";

    }


    //회원가입 로직 시작
    @PostMapping(value = "/regUser1")
    public String insertUser(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "UserController : 회원가입 시작");


        // 회원가입 결과에 대한 메시지 전달할 변수
        String msg = "";
        String url = "";
        //웹 회원가입결과에 대한 메시지를 전달할변수
        UserInfoDTO tDTO = null;

        try {

            String user_id = CmmUtil.nvl(request.getParameter("user_id")); //회원아이디
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw")); // 비밀번호
            String user_name = CmmUtil.nvl(request.getParameter("user_name"));  //회원이름
            String user_nick = CmmUtil.nvl(request.getParameter("user_nick"));  //회원닉
            String email = CmmUtil.nvl(request.getParameter("user_email")); //이메일
            String addr = CmmUtil.nvl(request.getParameter("addr1")); //주소


            log.info("user_id :" + user_id);
            log.info("user_pw :" + user_pw);
            log.info("user_name :" + user_name);
            log.info("user_nick :" + user_nick);
            log.info("user_email :" + email);
            log.info("user_addr :" + addr);

            //유저 정보를 담기위함
            tDTO = new UserInfoDTO();
            tDTO.setUser_id(user_id);
            tDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw)); // 비밀번호 해시 알고리즘 암호화
            tDTO.setUser_name(user_name);
            tDTO.setUser_nick(user_nick);
            tDTO.setUser_email(email);
            tDTO.setUser_address(addr);

            int res = UserService.insertUser(tDTO);


            if (res == 1) {
                msg = "회원가입이 되었습니다.";
                url = "/loginPage";
            } else if(res == 2) {
                msg = "이미 가입된 ID입니다";
                url = "/register/user";
            }else {
                msg = "오류로 인해 회원가입이 실패했습니다.";
                url = "/register/user";
                System.out.println("오류로 회원가입이 실패했습니다");
            }

        } catch (Exception e) {
            // 저장이 실패되면 유저 보여줄 메시지
            msg = "회원가입에 실패하였습니다.";
            log.info(e.toString());
            e.printStackTrace();
        } finally {
            log.info(this.getClass().getName() + "UserController : 회원가입 끝");

            // 회원가입 여부 결과 메시지 전달하기
            model.addAttribute("msg", msg);
            model.addAttribute("url", url);

            tDTO = null;
        }
        return "/alert/resultalert";
    }
    @RequestMapping(value = "/pwchange")
    public String pw() throws Exception{
        log.info(this.getClass().getName() + "비밀번호 찾기 페이지 시작");

        log.info(this.getClass().getName() + "비밀번호 찾기 페이지 끝");
        return "/login/pwchange";
    }
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public String pw(HttpServletRequest request, HttpServletResponse response, ModelMap model)throws Exception{

        log.info(this.getClass().getName() + "비밀번호 찾기 시작");
        int res = 0;
        int res2 = 0;
        String toMail = CmmUtil.nvl(request.getParameter("toMail"));
        String content = RandomStringUtils.getRamdomPassword(8);
        String user_id = CmmUtil.nvl(request.getParameter("user_id"));


        String mail_title = "비밀번호 변경 메일입니다.";
        String msg = "";
        String url = "";
        String icon = "";
        String contents = "";

        UserInfoDTO uDTO = new UserInfoDTO();
        uDTO.setUser_pw(content);
        uDTO.setUser_id(user_id);
        uDTO.setUser_email(toMail);


        res = UserService.updatepw(uDTO);
        if(res == 1){
            log.info("비밀번호 변경 성공하여 임시비밀번호를 메일로 발송");
            MailDTO mDTO = new MailDTO();
            mDTO.setToMail(toMail);
            mDTO.setContents(content);
            mDTO.setTitle(mail_title);
            res2 = MailService.sendMail(mDTO);
            if(res2 == 1){
                msg = "임시비밀번호 발송 성공";
                icon = "success";
                url = "/loginPage";
                contents = "임시비밀번호 발송에 성공하셨습니다";
            }else {
                msg = "임시비밀번호 발송 실패";
                icon = "warning";
                url = "/pwchange";
                contents = "임시비밀번호 발송에 실패하셨습니다";
            }
        }else {
            log.info("비밀번호 변경 실패 : 일치하는 정보 없음");
            msg = "임시비밀번호 발급 실패";
            contents = "입력받은 메일과 아이디로 일치하는 정보가 없어 임시비밀번호 발급 실패하였습니다.";
            icon = "warning";
            url = "/pwchange";


            model.addAttribute("res", res);
            model.addAttribute("msg", msg);
            model.addAttribute("icon", icon);
            model.addAttribute("contents", contents);
            model.addAttribute("url", url);

            return "/alert/resultalert";
        }


        model.addAttribute("res", res);
        model.addAttribute("msg", msg);
        model.addAttribute("icon", icon);
        model.addAttribute("contents", contents);
        model.addAttribute("url", url);
        log.info(this.getClass().getName() + "비밀번호 보내기 끝");

        return "/alert/resultalert";
    }

}
