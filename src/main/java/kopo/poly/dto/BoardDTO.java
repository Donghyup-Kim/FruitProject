package kopo.poly.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Data
@Setter
public class BoardDTO {

    private String board_seq; // 기본키, 순번
    private String board_title; // 제목
    private String board_contents; // 글 내용
    private String user_id; // 작성자
    private String reg_id; // 등록자 아이디
    private String reg_dt; // 등록일
    private String chg_id; // 수정자 아이디
    private String chg_dt; // 수정일


    }



