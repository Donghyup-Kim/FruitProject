package kopo.poly.service.impl;


import com.sun.org.apache.regexp.internal.RE;
import kopo.poly.dto.FileInfoDTO;
import kopo.poly.dto.ProductDTO;
import kopo.poly.persistance.mapper.IFileMapper;
import kopo.poly.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("FileService")
public class FileService implements IFileService {

    public final IFileMapper fileMapper;

    @Autowired
    public FileService(IFileMapper mapper, IFileMapper fileMapper){
        this.fileMapper = fileMapper;
    }


    @Override
    public int fileInfo(FileInfoDTO fDTO)throws Exception{

        log.info(this.getClass().getName() + "상품사진 정보 등록 시작");
        int res = 0;

        if(fDTO == null){
            fDTO = new FileInfoDTO();
        }
        res = fileMapper.fileInfo(fDTO);
        int fseq = fDTO.getFile_seq();
        log.info("file_info seq 번호 : " + fseq);

        if(res == 1){
            return fseq;
        }else {
            return res;
        }

    }


    @Override
    public int filedetailInfo(FileInfoDTO gDTO) throws Exception {
        log.info(this.getClass().getName() + "상품사진 상세 정보 등록 시작");

        int res = 0;

        if(gDTO == null){
            gDTO = new FileInfoDTO();
        }
        int success = fileMapper.filedetailInfo(gDTO);

        if(success > 0){
            res = 1;
            log.info(this.getClass().getName() + "상품사진 상세 정보 등록 완료");

        }else {
            res = 0;
            log.info(this.getClass().getName() + "상품사진 상세 정보 등록 실패");

        }
        return res;
    }

    @Override
    public List<FileInfoDTO> selectfilepath() throws Exception {

        log.info(this.getClass().getName() + "FileService selectfilepath 시작");

        List rList = new ArrayList<FileInfoDTO>();

        rList = fileMapper.selectfilepath();

        log.info("파일path : " + rList);

        log.info(this.getClass().getName() + "FileService selectfilepath 끝");

        return rList;
    }

    @Override
    public FileInfoDTO getimg(FileInfoDTO fDTO) throws Exception {

        if(fDTO == null){
            log.info("fDTO " + fDTO);
            fDTO = new FileInfoDTO();
        }
        FileInfoDTO iDTO = null;
        iDTO = new FileInfoDTO();

        log.info("iDTO : " + iDTO);

        iDTO = fileMapper.getimg(fDTO);

        return iDTO;
    }
}
