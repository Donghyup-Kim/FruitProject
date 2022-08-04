package kopo.poly.persistance.mapper;

import kopo.poly.dto.BoardDTO;
import kopo.poly.dto.FileInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper()
public interface IFileMapper {

    int fileInfo(FileInfoDTO fDTO)throws Exception;

    int filedetailInfo(FileInfoDTO gDTO)throws Exception;

    FileInfoDTO FileSeqSearch(FileInfoDTO uDTO) throws Exception;

    //index 이미지 파일
    List<FileInfoDTO> selectfilepath() throws Exception;

    FileInfoDTO getimg(FileInfoDTO fDTO) throws Exception;

}
