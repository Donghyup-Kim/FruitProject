package kopo.poly.service.impl;


import kopo.poly.dto.BoardDTO;
import kopo.poly.persistance.mapper.IBoardMapper;
import kopo.poly.service.IBoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("BoardService")
public class BoardService implements IBoardService {

    @Autowired
    private IBoardMapper boardMapper;

    //게시판 리스트 가져오기
    @Override
    public List<BoardDTO> getBoardList() throws Exception {

        log.info(this.getClass().getName() + "BoardService getBoardList 시작");

        log.info(this.getClass().getName() + "BoardService getBoardList 끝");
        return boardMapper.getBoardList();
    }
    //게시판 만들기
    @Override
    public void InsertBoardInfo(BoardDTO pDTO) throws Exception {

        boardMapper.InsertBoardInfo(pDTO);
    }

    //게시판 정보가져오기
    @Override
    public BoardDTO getBoardInfo(BoardDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + "BoardService getBoardInfo 시작");


        log.info(this.getClass().getName() + "BoardService getBoardInfo 끝");

        return boardMapper.getBoardInfo(pDTO);
    }


    //게시판 수정
    @Override
    public void updateBoardInfo(BoardDTO pDTO) throws Exception {
        log.info("pDTO : " + pDTO);

        boardMapper.updateBoardInfo(pDTO);
    }

    //게시판 삭제
    @Override
    public void deleteBoardInfo(BoardDTO pDTO) throws Exception {

        boardMapper.deleteBoardInfo(pDTO);
    }
}
