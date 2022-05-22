package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service("UserService")
public class UserService implements IUserService {
    @Resource
    private final IUserMapper UserMapper;

    @Autowired
    public UserService(IUserMapper userMapper){
        this.UserMapper = userMapper;
    }


    //로그인
    @Override
    public UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "UserService : getUserLoginCheck 시작");

        UserInfoDTO rDTO = UserMapper.getUserLoginCheck(pDTO);

        log.info(this.getClass().getName() + "UserService : getUserLoginCheck 끝");

        return rDTO;
    }
    //회원가입
    @Override
    public int insertUser(UserInfoDTO pDTO) throws Exception{

        log.info(this.getClass().getName() + "UserService : insertUser 시작!");

        //회원가입 성공 : 1, 아이디 중복 : 2, 에러 : 0
        int res = 0;

        if (pDTO == null) {
            pDTO = new UserInfoDTO();
        }

        UserInfoDTO rDTO = UserMapper.getUserLoginCheck(pDTO);

        if(rDTO == null){
            rDTO = new UserInfoDTO();
        }

        if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")){
            //아이디 중복이므로 회원가입 x
            res = 2;

        }else {
            res = UserMapper.insertUser(pDTO);
        }
        return res;
    }


}
