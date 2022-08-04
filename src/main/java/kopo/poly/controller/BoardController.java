package kopo.poly.controller;


import kopo.poly.dto.BoardDTO;
import kopo.poly.service.IBoardService;
import kopo.poly.util.CmmUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BoardController {

    @Resource(name = "BoardService")
    private IBoardService BoardService;



    //게시판 리스트
    @RequestMapping(value = "/board/boardlist")
    public String BoardList(HttpServletRequest request, HttpServletResponse response, ModelMap model)
            throws Exception {

        log.info(this.getClass().getName() + "BoardController 게시판리스트가져오기 시작");


        List<BoardDTO> rList = BoardService.getBoardList();

        if(rList == null) {
            rList = new ArrayList<BoardDTO>();

        }


        //리스트 결과값 넣어주기
        model.addAttribute("rList", rList);

        rList = null;

        log.info(this.getClass().getName() + "BoardController 게시판리스트가져오기 끝");

        return "/Board/BoardList";
    }

    //게시글 작성 페이지
    @GetMapping(value = "board/boardwrite")
        public String BoardWrite(){

        return "/Board/BoardWrite";
    }

    //게시판 작성 로직
    @PostMapping(value = "/boardwrite1")
    public String BoardInsert(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                              ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "BoardController : 게시판작성로직 시작");
        String msg = "";
        String url = "";
        String icon = "";

        String title = CmmUtil.nvl(request.getParameter("title"));
        String content = CmmUtil.nvl(request.getParameter("content"));
        String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
        log.info("user_id :" + user_id);

        BoardDTO pDTO = new BoardDTO();
        pDTO.setBoard_title(title);
        pDTO.setBoard_content(content);
        pDTO.setReg_id(user_id);


        log.info(title);
        log.info(content);

        BoardService.getBoardList();
        BoardService.InsertBoardInfo(pDTO);

        msg = "작성완료!";
        url = "/board/boardlist";
        icon = "success";


        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        model.addAttribute("icon", icon);

        log.info(this.getClass().getName() + "BoardController : 게시판작성로직 끝");

        return "/Board/BoardWriteResult";
    }
    //게시글 글 수정 로직
    @RequestMapping(value = "board/BoardUpdate1")
    public String BoardUpdat1e(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws Exception {

        log.info(this.getClass().getName() + "BoardController :  업데이트 시작");

        String msg = "";
        String url = "";
        String icon = "";

        String title = CmmUtil.nvl(request.getParameter("title"));
        String content = CmmUtil.nvl(request.getParameter("content"));
        String Board_seq = CmmUtil.nvl(request.getParameter("Board_seq"));



        log.info("Board_seq : " + Board_seq);
        log.info("title : " + title);
        log.info("content : "  + content);


        BoardDTO pDTO = new BoardDTO();
        BoardDTO oDTO = new BoardDTO();

        oDTO.setBoard_seq(Board_seq);
        pDTO.setBoard_title(title);
        pDTO.setBoard_content(content);
        pDTO.setBoard_seq(Board_seq);

        BoardDTO qDTO = BoardService.getBoardInfo(oDTO);
        BoardService.updateBoardInfo(pDTO);

        if (qDTO == null) {
            qDTO = new BoardDTO();


        }



        if (pDTO == null) {
            log.info("pDTO는 널입니다");
            pDTO = new BoardDTO();

        }

        model.addAttribute("msg", msg);
        model.addAttribute("url", url);
        model.addAttribute("icon", icon);


        log.info(this.getClass().getName() + "BoardController : 게시판 업데이트 끝");


        return "/alert/resultalert";


    }
    //게시판 수정 페이지
    @GetMapping(value = "board/boardeditpage")
    public String updatepage(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        log.info(this.getClass().getName() + "BoardController : 업데이트 페이지 시작");

        String msg = "";

        String Board_seq = CmmUtil.nvl(request.getParameter("Board_seq"));

        BoardDTO pDTO = new BoardDTO();

        pDTO.setBoard_seq(Board_seq);

        log.info("Boardseq의 값은??" + Board_seq);


        model.addAttribute("pDTO", pDTO);

        log.info(this.getClass().getName() + "BoardController : 업데이트 페이지 끝");

        return "/Board/BoardEditInfo";
    }


    //게시글 삭제
    @GetMapping(value = "board/BoardDelete")
    public String BoardDelete(HttpSession session, HttpServletRequest request, HttpServletResponse response, ModelMap model)
        throws Exception{

        log.info(this.getClass().getName() + "BoardController : 게시판 삭제 시작");

        String msg = "";
        String icon = "";
        String url = "";



        String Board_seq = CmmUtil.nvl(request.getParameter("Board_seq"));

        log.info("Board_seq :" + Board_seq);

        BoardDTO pDTO = new BoardDTO();

        pDTO.setBoard_seq(Board_seq);

        BoardService.deleteBoardInfo(pDTO);

        msg = "삭제";
        icon = "warning";
        url = "/board/boardlist";

        pDTO = null;

        log.info(this.getClass().getName() + "BoardController : 게시판 삭제 시작");

        model.addAttribute("msg", msg);
        model.addAttribute("icon",icon);
        model.addAttribute("url",url);


        return "/alert/resultalert";

    }

    //게시판 상세보기
    @GetMapping(value="/board/boardInfo")
    public String BoardInfo(HttpServletRequest request, HttpServletResponse response,
                             ModelMap model) throws Exception {

        log.info(this.getClass().getName() + "BoardController : 게시판 상세보기 시작");

        String msg = "";

        String nSeq = CmmUtil.nvl(request.getParameter("nSeq"));

        log.info("nSeq : " + nSeq);

        BoardDTO pDTO = new BoardDTO();

        pDTO.setBoard_seq(nSeq);

        BoardDTO rDTO = BoardService.getBoardInfo(pDTO);

        if (rDTO == null) {
                rDTO = new BoardDTO();

        }

        model.addAttribute("rDTO", rDTO);

        log.info(this.getClass().getName() + "BoardController : 게시판 상세보기 끝");

        model.addAttribute("msg",msg);


        return "/Board/Boarddetail";
    }

}
