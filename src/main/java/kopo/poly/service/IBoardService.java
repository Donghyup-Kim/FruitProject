package kopo.poly.service;


import kopo.poly.dto.BoardDTO;

import java.util.List;


public interface IBoardService {

    List<BoardDTO> getBoardList() throws Exception;

    void InsertBoardInfo(BoardDTO pDTO) throws Exception;

    BoardDTO getBoardInfo(BoardDTO pDTO) throws Exception;

    void updateBoardInfo(BoardDTO pDTO) throws Exception;

    void deleteBoardInfo(BoardDTO pDTO) throws Exception;
}
