package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Slf4j
@Controller
public class UserpageController {

    //유저페이지
    @GetMapping(value = "/userpage")
    public  String userpage() throws Exception{

        log.info(this.getClass().getName() + "UserpageController : 유저페이지 시작");

        log.info(this.getClass().getName() + "UserpageController : 유저페이지 끝");

        return "/mypage/Userpage";

    }
}
