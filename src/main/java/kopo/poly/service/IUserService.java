package kopo.poly.service;


import kopo.poly.dto.UserInfoDTO;

public interface IUserService {

    //로그인
    UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

    int insertUser(UserInfoDTO pDTO) throws Exception;
}
