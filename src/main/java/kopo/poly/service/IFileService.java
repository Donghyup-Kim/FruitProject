package kopo.poly.service;

import kopo.poly.dto.FileInfoDTO;
import kopo.poly.dto.ProductDTO;

import java.util.List;

public interface IFileService {

    int fileInfo(FileInfoDTO fDTO) throws Exception;

    int filedetailInfo(FileInfoDTO gDTO) throws Exception;

    List<FileInfoDTO> selectfilepath() throws Exception;

    FileInfoDTO getimg(FileInfoDTO fDTO) throws Exception;
}
