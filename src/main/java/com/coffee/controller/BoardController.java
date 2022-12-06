package com.coffee.controller;

import com.coffee.domain.BoardDto;
import com.coffee.domain.PageHandler;
import com.coffee.domain.SearchCondition;
import com.coffee.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    // 게시물 목록 가져오기
    @GetMapping("/list")
    public String list(SearchCondition sc, Model m, HttpServletRequest request) {

        if(!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        try {
            int totalCnt = boardService.searchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.searchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());

        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "List_Error");
            m.addAttribute("totalCnt", 0);
        }
        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("user_id")!=null;
    }

    // 게시물 작성하기
    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) { // boardDto로 입력한 게시물 받아와야 함
        String board_writer = (String)session.getAttribute("user_id");
        boardDto.setBoard_writer(board_writer);

        try {
            int rowCnt = boardService.write(boardDto); // insert

            if(rowCnt!=1) {
                throw new Exception("Write_Failed"); // insert가 되지 않으면 예외를 던져서 게시판으로 가지않고 catch문으로 보내기
            }
            rattr.addFlashAttribute("msg", "Write_Done"); // session을 이용한 일회성 저장
            return "redirect:/board/list"; // 성공적으로 게시물 등록이 되면 게시물 목록(게시판)으로 돌아가기
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto); // m에 boardDto를 넣어줘야 에러 발생 했을 때 작성해뒀던 내용이 유지된 상태로 board.jsp 화면으로 돌아올 수 있음
            m.addAttribute("msg", "Write_Error");

            return "board"; // 예외 발생 시 글쓰기 화면을 다시 보여줘야 함
        }
    }

    // 게시물을 작성하기 위한 화면 보여주는 /write
    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");
        return "board"; // board.jsp를 읽기와 쓰기에 사용. '쓰기'에 사용할 때는 mode를 new로 지정
    }

    // 게시물 수정하기
    @PostMapping("/modify")
    public String modify(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) { // boardDto로 입력한 게시물 받아와야 함
        String board_writer = (String)session.getAttribute("user_id");
        boardDto.setBoard_writer(board_writer);

        try {
            int rowCnt = boardService.modify(boardDto); // update

            if(rowCnt!=1) {
                throw new Exception("Modify_Failed"); // modofy가 되지 않으면 예외를 던져서 게시판으로 가지않고 catch문으로 보내기
            }
            rattr.addFlashAttribute("msg", "Modify_Done"); // 이건 session을 이용한 일회성 저장

            return "redirect:/board/list"; // 게시물 목록(게시판)으로 돌아가기
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("msg", "Modify_Error");

            return "board"; // 예외 발생 시 수정 화면을 다시 보여줘야 함
        }
    }

    // 게시물 삭제하기
    @PostMapping("/remove")
    public String remove(Integer board_no, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        String board_writer = (String) session.getAttribute("user_id");
        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);

            // 게시물 삭제할때는 게시물 번호랑 작성자 둘 다 가져와서 삭제해야 함. 삭제가 됐으면 rowCnt=1
            int rowCnt = boardService.remove(board_no, board_writer);

            if(rowCnt!=1) {
                throw new Exception("board remove error");
            }
            rattr.addFlashAttribute("msg", "Delete_Done"); // rattr은 session에 잠깐 저장했다가 지워버리는 1회용 데이터라고 보면 됨
        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "Delete_Error");
        }
        return "redirect:/board/list";
    }

    // 지정된 번호의 게시물 보여줌
    @GetMapping("/read")
    public String read(Integer board_no, Integer page, Integer pageSize, Model m){
        try {
            BoardDto boardDto = boardService.read(board_no); // BoardService를 통해 읽어온 board_no가 boardDto에 담김
            m.addAttribute(boardDto); // attribute 이름을 생략하면 타입의 첫글자를 소문자로 바꿔서 이름으로 저장해줌
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }
}