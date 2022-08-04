package kopo.poly.service;


import kopo.poly.dto.UserInfoDTO;
import org.apache.catalina.User;

public interface IUserService {

    //로그인
    int getUserLoginCheck(UserInfoDTO tDTO) throws Exception;

    int insertUser(UserInfoDTO tDTO) throws Exception;

    UserInfoDTO selectUserInfo(UserInfoDTO uDTO)throws Exception;

    int updatepw(UserInfoDTO uDTO) throws Exception;
}
