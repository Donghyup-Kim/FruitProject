package kopo.poly.dto;


import com.amazonaws.services.dynamodbv2.xspec.S;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Data
@Setter
public class OrderDTO {

    private int ord_seq; // 기본키, 순번
    private String ord_id; // 제목
    private String ord_time; // 글 내용
    private String product_seq;
    private String pro_title;
    private String pro_content;
    private String pro_price;
    private String pro_type;
    private String pro_reg_id;
    private String pro_seq;
    private String file_seq;

    private String exists_yn;


    }



