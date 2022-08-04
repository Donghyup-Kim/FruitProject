package kopo.poly.service;


import kopo.poly.dto.SellerInfoDTO;


public interface ISellerService {

    //로그인
    int SellerLoginCheck(SellerInfoDTO pDTO) throws Exception;

    //회원가입
    int insertSeller(SellerInfoDTO pDTO) throws Exception;

}
