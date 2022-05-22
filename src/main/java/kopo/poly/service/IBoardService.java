package kopo.poly.service;


import kopo.poly.dto.BoardDTO;

import java.util.List;


public interface IBoardService {

    List<BoardDTO> getNoticeList() throws Exception;

    void InsertNoticeInfo(BoardDTO pDTO) throws Exception;

    BoardDTO getNoticeInfo(BoardDTO pDTO) throws Exception;

    void updateNoticeInfo(BoardDTO pDTO) throws Exception;

    void deleteNoticeInfo(BoardDTO pDTO) throws Exception;
}
