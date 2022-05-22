package kopo.poly.service.impl;


import kopo.poly.dto.BoardDTO;
import kopo.poly.persistance.mapper.INoticeMapper;
import kopo.poly.service.IBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("NoticeService")
public class BoardService implements IBoardService {

    @Autowired
    private INoticeMapper noticeMapper;

    //게시판 리스트 가져오기
    @Override
    public List<BoardDTO> getNoticeList() throws Exception {

        log.info(this.getClass().getName() + "NoticeService getNoticeList 시작");

        log.info(this.getClass().getName() + "NoticeService getNoticeList 끝");
        return noticeMapper.getNoticeList();
    }
    //게시판 만들기
    @Override
    public void InsertNoticeInfo(BoardDTO pDTO) throws Exception {

        noticeMapper.InsertNoticeInfo(pDTO);
    }

    //게시판 정보가져오기
    @Override
    public BoardDTO getNoticeInfo(BoardDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "NoticeService getNoticeInfo 시작");

        log.info(this.getClass().getName() + "NoticeService getNoticeInfo 끝");

        return noticeMapper.getNoticeInfo(pDTO);
    }


    //게시판 수정
    @Override
    public void updateNoticeInfo(BoardDTO pDTO) throws Exception {

        noticeMapper.updateNoticeInfo(pDTO);
    }

    //게시판 삭제
    @Override
    public void deleteNoticeInfo(BoardDTO pDTO) throws Exception {

        noticeMapper.deleteNoticeInfo(pDTO);
    }
}
