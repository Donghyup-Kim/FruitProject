package kopo.poly.persistance.mapper;

import kopo.poly.dto.SellerInfoDTO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ISellerMapper {


    //회원가입 중복확인
    SellerInfoDTO getSellExists(SellerInfoDTO pDTO) throws Exception;

    SellerInfoDTO getSellerLogin(SellerInfoDTO pDTO) throws Exception;
    //판매자 회원가입
    int insertSeller(SellerInfoDTO pDTO) throws Exception;

}
