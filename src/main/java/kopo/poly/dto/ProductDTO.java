package kopo.poly.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Data
@Setter
public class ProductDTO {

    private int pro_seq; // 기본키, 순번
    private int file_seq;
    private String pro_name; // 상품명
    private String pro_title;
    private String pro_img; // 상품 이미지

    private String pro_content; // 상품내용
    private String pro_price; //상품가격
    private String pro_type;
    private String pro_reg_id;
    private String pro_value;//텐서플로우에서 받아온 등급값
    private String sell_id;


    }



