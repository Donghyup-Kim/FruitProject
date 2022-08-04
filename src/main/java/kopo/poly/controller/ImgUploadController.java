package kopo.poly.controller;

import com.amazonaws.services.codecommit.model.UserInfo;
import kopo.poly.dto.FileInfoDTO;
import kopo.poly.dto.ProductDTO;
import kopo.poly.dto.SellerInfoDTO;
import kopo.poly.dto.UserInfoDTO;
import kopo.poly.service.IFileService;
import kopo.poly.service.IProductService;
import kopo.poly.service.IS3Service;
import kopo.poly.util.CmmUtil;
import kopo.poly.util.DateUtil;
import kopo.poly.util.RandomStringUtils;
import kopo.poly.util.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import sun.net.util.URLUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class ImgUploadController {

    @Resource(name = "S3Service")
    public IS3Service S3Service;

    @Resource(name = "FileService")
    public IFileService FileService;

    @Resource(name = "ProductService")
    public IProductService ProductService;


    @ResponseBody
    @PostMapping(value = "img/imgUpload")
    public int imgUpload(HttpServletRequest request, HttpSession session,
                         @RequestParam(value = "fileUpload") MultipartFile mf) throws Exception {
        log.info(this.getClass().getName() + "이미지 업로드 컨트롤러 시작!");

        int res = 0; // if 파일 정보가 insert 되면 res == file_seq, fail일 경우 res ==0
        int res2 = 0; // 파일 상세저보가 insert 되면 1 아니면 0
        int res3 = 0;

        String py = "";

        FileInfoDTO fDTO = null;
        FileInfoDTO oDTO = null;
        FileInfoDTO gDTO = null;
        SellerInfoDTO tDTO = null;
        ProductDTO rDTO = null;


        //누가 등록자, 수정자 파악용

        // String user_id = (String) session.getAttribute("user_id");
        String sell_id = CmmUtil.nvl((String) session.getAttribute("SS_SELL_ID"));
        // 분류를 위한 file_code

        String seq = "";
        //파일 원본파일명 가져오기
        String originalFileName = mf.getOriginalFilename();
        //파일 뒤 확장자만 가져오기
        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();


        //위조건에 만족하는 확장자만 실행
        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {
            // 저장되는 파일이름 (영어, 숫자로 파일명 변경)
            String saveFileName = DateUtil.getDateTime("24hhmmss") + "." + ext;
            //저장경로
            String file_path = S3Service.fileUpload(mf, saveFileName);

            //풀 경로
            String fullFileInfo = file_path + "/" + saveFileName;

            //구분
            String fileOriginCode = RandomStringUtils.getRamdomPassword(8);

            log.info("파일 구분 코드 : " + originalFileName);
            log.info("originalFileName : " + originalFileName);
            log.info("ext : " + ext);
            log.info("saveFileName 서버에 저장된 파일이름 : " + saveFileName);
            log.info("saveFilePath 서버에 저장된 파일의 경로 : " + file_path);
            log.info("fullFileInfo 서버에 저장된 파일의 이름, 확장자 : " + fullFileInfo);

            String file_code = RandomStringUtils.getRamdomPassword(8);
            log.info("임시발급 파일 구분 코드 : " + file_code);


            /*----------------파일정보 저장---------------------*/
            fDTO = new FileInfoDTO();
            fDTO.setFile_code(file_code);
            res = FileService.fileInfo(fDTO);


            if (res >= 1) {
                log.info("파일 시퀀스 값은 : " + res);
                log.info("FILE_INFO 테이블에 정상적으로 데이터가 저장됨");

                gDTO = new FileInfoDTO();

                //상세정보 저장
                gDTO.setFile_seq(res);
                gDTO.setFile_detail_name(saveFileName);
                gDTO.setFile_detail_path(file_path);
                gDTO.setFile_detail_org_name(originalFileName);
                gDTO.setFile_detail_ext(ext);

                res2 = FileService.filedetailInfo(gDTO);


            } else {
                log.info("res 값은?? : " + res);
            }
            if (res2 == 1) {
                log.info("detailinfo 테이블에 데이터 저장됨");
                tDTO = new SellerInfoDTO();
                tDTO = (SellerInfoDTO) session.getAttribute("oDTO");

                //플라스크 통신 로직
                UrlUtil uu = new UrlUtil();

                String url = "http://localhost:5004";
                String api = "/image?";
                String api2 = "/saveFileName?";
                String iname = "saveFileName=";
                String ipath = saveFileName;
                String iname2 = "saveFileName=";

                log.info("url :" + url + "api : " + api + "api : " + iname + "ipath : " + ipath);
                //saveFileName값을 줘서 플라스크 /saveFileName 경로로 보냄
                py = uu.urlReadforString(url + api2 + iname2 + saveFileName);
                uu = null;
                UrlUtil uu2 = new UrlUtil();
                //saveFileName 경로에서 받아온 success가 돌아 왔을때 텐서플로우 이미지 등급분류 로직에 보냄
                if (py.equals("success")) {
                    py = null;
                    py = uu2.urlReadforString(url + api + iname + ipath);

                    log.info("py의 값은?? " + py);
                }
                String pro_value = py;
                log.info("py: " +py);
                String pro_title = CmmUtil.nvl(request.getParameter("pro_title"));
                String pro_content = CmmUtil.nvl(request.getParameter("pro_content"));
                String pro_price = CmmUtil.nvl(request.getParameter("pro_price"));
                String pro_type = CmmUtil.nvl(request.getParameter("pro_type"));
                String pro_reg_id = sell_id;


                rDTO = new ProductDTO();
                rDTO.setPro_title(pro_title);
                rDTO.setPro_content(pro_content);
                rDTO.setPro_price(pro_price);
                rDTO.setPro_type(pro_type);
                rDTO.setPro_value(pro_value);
                rDTO.setPro_reg_id(pro_reg_id);

                rDTO.setFile_seq(res);

                res3 = ProductService.InsertProductFileInfo(rDTO);


                if (res3 == 1) {
                    log.info("등록 성공! : 등록이 성공하였습니다.");
                } else if (res3 == 2) {
                    log.info("등록 실패! :  이미 존재합니다.");
                } else {
                    log.info("등록 실패! : 오류로 인해 등록이 실패하였습니다.");
                }
            } else {
                log.info("FILE_MORE_INFO 테이블에 데이터 저장 실패 !");
            }


        }return res2;
    }
}




