package kopo.poly.service.impl;

import kopo.poly.dto.SellerInfoDTO;
import kopo.poly.persistance.mapper.ISellerMapper;
import kopo.poly.service.ISellerService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service("SellerService")
public class SellerService implements ISellerService {
    private final ISellerMapper SellerMapper;

    @Autowired
    public SellerService(ISellerMapper sellerMapper) {this.SellerMapper = sellerMapper;}

    //로그인
    @Override
    public SellerInfoDTO SellerLoginCheck(SellerInfoDTO pDTO) throws Exception {

       log.info(this.getClass().getName() + "SellerService : SellerLogin 시작");

       SellerInfoDTO rDTO = SellerMapper.SellerLoginCheck(pDTO);

       log.info(this.getClass().getName() + "SellerService : SellerLogin 끝");


       return rDTO;
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
        SellerInfoDTO rDTO = SellerMapper.SellerLoginCheck(pDTO);

        if(rDTO == null) {
            rDTO = new SellerInfoDTO();

        }

        if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")){
            //아이디 중복이므로 회원가입 x
            res = 2;

        }else {
            res = SellerMapper.insertSeller(pDTO);
        }
        return res;
    }

}





