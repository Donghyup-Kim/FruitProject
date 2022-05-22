package kopo.poly.controller;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class UserController {

    @Resource(name = "UserService")
    private IUserService UserService;


    //로그인페이지 로직
    @GetMapping(value = "/loginPage1")
    public  String loginPage(HttpServletRequest request, HttpSession session, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + "UserController : 로그인페이지 시작");

        String msg = "";
        String url = "";

        int res = 0;

        UserInfoDTO pDTO = null; // 웹에서 받은 정보를 저장
        UserInfoDTO rDTO = null; // DB에서 받아온 정보를 저장하여 jsp 넘김

        try {
            String user_id = CmmUtil.nvl(request.getParameter("user_id"));
            String user_pw = CmmUtil.nvl(request.getParameter("user_pw"));

            log.info("user_id : " + user_id);
            log.info("user_pw : " + user_pw);

            pDTO = new UserInfoDTO();
            pDTO.setUser_id(user_id);
            pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw));

            rDTO = UserService.getUserLoginCheck(pDTO);

            if (CmmUtil.nvl(rDTO.getUser_id()).length() > 0) {

                msg = rDTO.getUser_id() + "님 로그인 성공!";
                url = "/index";
                session.setAttribute("user_id", rDTO.getUser_id());
                session.setAttribute("user_pw", rDTO.getUser_pw());


            } else {
                url = "/login/userlogin";
                msg = "ID.PASSWORD 재확인 부탁드립니다.";
            }
        } catch (Exception e) {
            log.info(e.toString());
            e.printStackTrace();
        } finally {

                log.info(this.getClass().getName() + "UserController : 로그인페이지 끝!");
                model.addAttribute("msg", msg);
                model.addAttribute("url", url);

                return "/index";
            }
        }

    @GetMapping(value = "/userlogout") // 로그아웃
    public String userLogout(HttpServletRequest request, ModelMap model) {
        log.info(this.getClass().getName() + ".user/userLogout start");
        HttpSession session = request.getSession();

        String url = "/index";
        String msg = "로그아웃 성공";
        session.invalidate(); // session clear
        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        return "/index";
    }
    //로그인 페이지
    @GetMapping(value = "/loginPage")
    public  String userlogin() throws Exception{

        log.info(this.getClass().getName() + "UserController 로그인페이지 시작");

        log.info(this.getClass().getName() + "UserController 로그인페이지 끝");

        return "/login/userlogin";

    }


    //인덱스 페이지
    @GetMapping(value = "/index")
    public  String indexpage() throws Exception{

        log.info(this.getClass().getName() + "index : 인덱스페이지 시작");

        log.info(this.getClass().getName() + "index : 인덱스페이지 끝");

        return "/index";

    }
    //유저 마이페이지
    @GetMapping(value = "/Userpage")
    public  String Userpage() throws Exception{

        log.info(this.getClass().getName() + "controller : 유저페이지 시작");

        log.info(this.getClass().getName() + "controller : 유저페이지 끝");

        return "mypage/Userpage";

    }
    //회원가입페이지
    @GetMapping(value = "/regUser")
    public  String regUser() throws Exception{

        log.info(this.getClass().getName() + "usercontrolloer : 회원가입 페이지 시작");

        log.info(this.getClass().getName() + "usercontroller : 회원가입 페이지 끝");

        return "/register/user";

    }


    //회원가입 로직 시작
    @GetMapping(value = "/regUser1")
    public String insertUser(HttpServletRequest request, ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "UserController : 회원가입 시작");

        int res = 0;  //수정 제대로 됐는지 확인하기

        // 회원가입 결과에 대한 메시지 전달할 변수
        String msg = "";
        String url = "";

        //웹 회원가입결과에 대한 메시지를 전달할변수
        UserInfoDTO pDTO = null;

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
            pDTO = new UserInfoDTO();
            pDTO.setUser_id(user_id);
            pDTO.setUser_pw(EncryptUtil.encHashSHA256(user_pw)); // 비밀번호 해시 알고리즘 암호화
            pDTO.setUser_name(user_name);
            pDTO.setUser_nick(user_nick);
            pDTO.setUser_email(EncryptUtil.encAES128CBC(email)); // 이메일 AES-128-CBC 암호화
            pDTO.setUser_address(addr);

            res = UserService.insertUser(pDTO);


            if (res == 1) {
                msg = "회원가입이 되었습니다.";
                url = "/login/userlogin";
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

            pDTO = null;
        }
        return "/login/userlogin";
    }

}
