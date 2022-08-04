package kopo.poly.controller;

import kopo.poly.dto.FileInfoDTO;
import kopo.poly.dto.ProductDTO;
import kopo.poly.service.IFileService;
import kopo.poly.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
public class MainController {

    @Resource(name = "ProductService")
    private IProductService ProductService;

    @Resource(name = "FileService")
    private IFileService FileService;


    //인덱스 페이지
    @GetMapping(value = "/index")
    public String indexpage(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws Exception{
        log.info(this.getClass().getName() + "index 페이지 시작");
        List<ProductDTO> pList = ProductService.getProductList();
        log.info("메인컨트롤러 이미지파일 서칭 시작");
        List<FileInfoDTO> rList = FileService.selectfilepath();



        if (pList == null){

            pList = new ArrayList<ProductDTO>();

        }else if(rList == null){
            rList = new ArrayList<FileInfoDTO>();
        }

        model.addAttribute("pList", pList);
        model.addAttribute("rList", rList);




        log.info(this.getClass().getName() + "index 페이지 끝");

        return "/index";

    }

    //상품 상세정보페이지
    @GetMapping(value = "/productpage")
    public String productpage(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception{

        ProductDTO rDTO = null;
        ProductDTO nDTO = null;
        FileInfoDTO oDTO = null;
        FileInfoDTO fDTO = null;

        int file_seq = (int)Integer.parseInt(request.getParameter("productno"));

        log.info("JSP에서 받아온 SEQ값 : " +  file_seq);

        log.info(this.getClass().getName() + "상품상세정보페이지 시작");

        rDTO = new ProductDTO();
        nDTO = new ProductDTO();
        //rDTO에 JSP에서 받아온 SEQ값을 담아 서비스로 전달
        rDTO.setFile_seq(file_seq);
        //서비스에서 받아온 rDTO값을 nDTO에 저장
        nDTO = ProductService.seqSearch(rDTO);
        log.info("nDTO 값은" + nDTO);
        /////////////////////////////////////

        int fil_seq = nDTO.getFile_seq();
        oDTO = new FileInfoDTO();
        oDTO.setFile_seq(fil_seq);

        fDTO = new FileInfoDTO();
        fDTO = FileService.getimg(oDTO);

        model.addAttribute("nDTO",nDTO);
        model.addAttribute("fDTO",fDTO);

        log.info("nDTO 값은" + nDTO);
        log.info("fDTO 값은" + fDTO);

        log.info(this.getClass().getName() + "상품상세정보페이지 끝");

        return "/Product/Productdetail";

    }

    @GetMapping("/itemprice")
    public String testItem(ModelMap model) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://0.0.0.0:5004/items";

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

        List<Map<String, Object>> itemList = (List<Map<String, Object>>) response.getBody().get("resource");

        log.info("itemList의 값은?? " + itemList);

        for(Map<String, Object> item : itemList) {
            Integer price1 = (Integer) item.get("1");
            Integer price2 = (Integer)  item.get("2");
            String title = (String) item.get("title");

            log.info("price1 : "+price1);
            log.info("price2 : "+price2);
            log.info("title : "+title);
        }

        model.addAttribute("itemList",itemList);

        return "/Product/ProductPrice";
    }




}