package kopo.poly.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SellerInfoDTO {

    private String sell_id;  //사용자 아이디
    private String sell_pw; //사용자비밀번호
    private String sell_name; //사용자이름
    private String sell_nick; //사용자닉네임
    private String sell_email; //사용자이메일
    private String sell_address; //사용자 주소
    private String reg_id;
    private String reg_dt;
    private String chg_id;
    private String chg_dt;


    // 회원 가입시, 중복가입을 방지하기 위해 사용할 변수
    // DB조회 해서 회원이 존재하면 Y값을 반환함
    // DB테이블에 본재하지 않는 가상의 칼럼(ALIAS)
    private String exists_yn;
 

}
