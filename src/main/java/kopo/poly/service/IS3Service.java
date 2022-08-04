package kopo.poly.service;

import kopo.poly.dto.FileInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;


public interface IS3Service {

    String fileUpload(MultipartFile mf, String dirName)throws Exception;

    int fileDelete(String fileName) throws Exception;

    }
