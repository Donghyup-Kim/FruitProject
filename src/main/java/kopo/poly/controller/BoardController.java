package kopo.poly.controller;


import kopo.poly.dto.BoardDTO;
import kopo.poly.service.IBoardService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Resource(name = "NoticeService")
    private IBoardService noticeService;


    //게시판 리스트
    @GetMapping(value = "notice/Notice")
    public String NoticeList(HttpServlet request, HttpServletResponse response, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + "NoticeController 게시판리스트가져오기 시작");

        List<BoardDTO> rList = noticeService.getNoticeList();

        if(rList == null) {
            rList = new ArrayList<BoardDTO>();
        }

        model.addAttribute("rList", rList);

        rList = null;

        log.info(this.getClass().getName() + "NoticeController 게시판리스트가져오기 끝");

        return "notice/NoticeList";
    }

    //게시판 작성

    @GetMapping(value = "notice/NoticeWrite")
    public String NoticeWrite(HttpServlet request, HttpServletResponse response, ModelMap model, HttpSession session)
        throws Exception {

        log.info(this.getClass().getName() + "NoticeController 게시판등록 시작");



            return "notice/NoticeWrite";
    }


}
