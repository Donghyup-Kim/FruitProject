package kopo.poly.service.impl;

import kopo.poly.dto.SellerInfoDTO;
import kopo.poly.persistance.mapper.ISellerMapper;
import kopo.poly.service.ISellerService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service("SellerService")
public class SellerService implements ISellerService {

    @Resource(name = "ISellerMapper")
    private ISellerMapper SellerMapper;

    //로그인
    @Override
    public int SellerLoginCheck(SellerInfoDTO pDTO) throws Exception {

       log.info(this.getClass().getName() + "SellerService : SellerLogin 시작");

       int res = 0;

       SellerInfoDTO rDTO = SellerMapper.getSellerLogin(pDTO);

       if(rDTO == null) {
           log.info(this.getClass().getName() + "로그인 실패");
           res = 0;
       }else {
           log.info(this.getClass().getName() + "로그인 성공");
           res = 1;
       }

       log.info(this.getClass().getName() + "SellerService : SellerLogin 끝");


       return res;
    }

    //회원가입
    @Override
    public int insertSeller(SellerInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "SellerService : insertSeller 시작");


        //회원가입 성공 :1 ,아이디 중복 : 2, 에러 : 0
        int res = 0;

        if(pDTO == null) {
            pDTO = new SellerInfoDTO();

        }
        SellerInfoDTO rDTO = SellerMapper.getSellExists(pDTO);
        log.info("중복체크");

        if(rDTO == null) {
            rDTO = new SellerInfoDTO();

        }

        if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")){
            //아이디 중복이므로 회원가입 x
            res = 2;

        }else {
            int success = SellerMapper.insertSeller(pDTO);

            if(success > 0){

                res = 1;
            }else {
                res = 0;
            }
        }
        return res;
    }

}





