package kopo.poly.persistance.mapper;


import kopo.poly.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IUserMapper {

    // 회원가입 중복확인
    UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;

    UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception;

    //유저 회원가입
    int insertUser(UserInfoDTO pDTO) throws Exception;
    

}
