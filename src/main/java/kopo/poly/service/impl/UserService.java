package kopo.poly.service.impl;

import kopo.poly.dto.UserInfoDTO;
import kopo.poly.persistance.mapper.IUserMapper;
import kopo.poly.service.IUserService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.plaf.PanelUI;


@Slf4j
@Service("UserService")
public class UserService implements IUserService {
    @Resource(name = "IUserMapper")
    private IUserMapper UserMapper;


    //회원가입
    @Override
    public int insertUser(UserInfoDTO pDTO) throws Exception{

        log.info(this.getClass().getName() + "UserService : insertUser 시작!");

        //회원가입 성공 : 1, 아이디 중복 : 2, 에러 : 0
        int res = 0;

        if (pDTO == null) {
            pDTO = new UserInfoDTO();
        }

        UserInfoDTO rDTO = UserMapper.getUserExists(pDTO);
        log.info("중복체크");

        if(rDTO == null){
            rDTO = new UserInfoDTO();
        }

        if(CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")){
            //아이디 중복이므로 회원가입 x
            res = 2;

        }else {
            int success = UserMapper.insertUser(pDTO);

            if(success > 0){

                res = 1;
            }
            else {
                res = 0;
            }
        }
        return res;
    }

    //로그인 체크
    @Override
    public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "UserService : getUserLoginCheck 시작");
        //성공: 1  실패: 0
        int res = 0;

        UserInfoDTO rDTO = UserMapper.getUserLoginCheck(pDTO);

        if(rDTO == null) {
            log.info(this.getClass().getName() + "로그인 실패");
            res = 0;
        }else {
            log.info(this.getClass().getName() + "로그인 성공");
            res = 1;
        }

        log.info(this.getClass().getName() + "UserService : getUserLoginCheck 끝");

        return res;
    }
    @Override
    public UserInfoDTO selectUserInfo(UserInfoDTO uDTO) throws Exception{
        log.info("UserService : selectUserInfo 시작");

        log.info("UserService : selectUserInfo 끝");
        return UserMapper.selectUserInfo(uDTO);
    }

    @Override
    public int updatepw(UserInfoDTO uDTO) throws Exception{
        log.info(this.getClass().getName() + "업데이트 pw 시작");

        int res = 0;

        if(uDTO == null) {
            uDTO = new UserInfoDTO();
        }
       int success = UserMapper.updatepw(uDTO);
        if(success > 0) {
            res = 1;
            log.info(this.getClass().getName() + "업데이트 성공");
        }else {
            log.info(this.getClass().getName() + "업데이트 실패");
        }

        log.info(this.getClass().getName() + "업데이트 pw 끝");

        return  res;
    }


}
