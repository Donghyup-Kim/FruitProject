package kopo.poly.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class FileInfoDTO {

    private int file_seq; // 저장된 이미지 파일의 파일 저장경로
    private String file_code; // 저장된 파일 이미지 파일 코드
    private int fk_file_seq; // 저장된 이미지 파일의 파일 저장경로
    private String file_detail_seq;
    private String file_detail_path;
    private String file_detail_name; // 저장된 파일 이미지 파일 이름
    private String file_detail_org_name; //
    private String file_detail_ext; //
}
