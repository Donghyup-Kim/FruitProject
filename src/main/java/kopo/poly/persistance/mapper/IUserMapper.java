package kopo.poly.persistance.mapper;


import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {

    // 로그인 체크
    UserInfoDTO getUserLoginCheck(UserInfoDTO tDTO) throws Exception;
    // 회원가입 중복확인
    UserInfoDTO getUserExists(UserInfoDTO tDTO) throws Exception;

    //유저 회원가입
    int insertUser(UserInfoDTO tDTO) throws Exception;

    UserInfoDTO selectUserInfo(UserInfoDTO uDTO) throws Exception;

    int updatepw(UserInfoDTO uDTO)throws Exception;

}
