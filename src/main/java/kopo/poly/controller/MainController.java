package kopo.poly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class MainController {

    //상품 상세정보페이지
    @GetMapping(value = "/productpage")
    public  String productpage() throws Exception{

        log.info(this.getClass().getName() + "ProductController : 상품정보페이지 시작");

        log.info(this.getClass().getName() + "ProductController : 상품정보페이지 끝");

        return "/Product/Productdetail";

    }
}
