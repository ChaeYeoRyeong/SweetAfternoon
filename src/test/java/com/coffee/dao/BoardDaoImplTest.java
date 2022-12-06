package com.coffee.dao;

import com.coffee.domain.BoardDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@WebAppConfiguration
@SpringBootTest
class BoardDaoImplTest {

    @Autowired
    private BoardDao boardDao;

    @Test
    public void insertTestData() throws Exception {
        boardDao.deleteAll();
        for (int i = 1; i <= 20; i++) {
            BoardDto boardDto = new BoardDto("제목"+i, "안녕하세요. "+i+"번째 게시물 입니다. 테스트 돌려서 입력한 내용입니다.", "asdf");
            boardDao.insert(boardDto);
        }
    }

    @Test
    public void countTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==1);

        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==2);
    }

    @Test
    public void deleteAllTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.deleteAll()==1);
        assertTrue(boardDao.count()==0);

        boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.deleteAll()==2);
        assertTrue(boardDao.count()==0);
    }

    @Test
    public void deleteTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        Integer board_no = boardDao.selectAll().get(0).getBoard_no();
        assertTrue(boardDao.delete(board_no, boardDto.getBoard_writer())==1);
        assertTrue(boardDao.count()==0);

        assertTrue(boardDao.insert(boardDto)==1);
        board_no = boardDao.selectAll().get(0).getBoard_no();
        assertTrue(boardDao.delete(board_no, boardDto.getBoard_writer()+"222")==0);
        assertTrue(boardDao.count()==1);

        assertTrue(boardDao.delete(board_no, boardDto.getBoard_writer())==1);
        assertTrue(boardDao.count()==0);

        assertTrue(boardDao.insert(boardDto)==1);
        board_no = boardDao.selectAll().get(0).getBoard_no();
        assertTrue(boardDao.delete(board_no+1, boardDto.getBoard_writer())==0);
        assertTrue(boardDao.count()==1);
    }

    @Test
    public void insertTest() throws Exception {
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);

        boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==2);

        boardDao.deleteAll();
        boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==1);
    }

    @Test
    public void selectAllTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        List<BoardDto> list = boardDao.selectAll();
        assertTrue(list.size() == 0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);

        list = boardDao.selectAll();
        assertTrue(list.size() == 1);

        assertTrue(boardDao.insert(boardDto)==1);
        list = boardDao.selectAll();
        assertTrue(list.size() == 2);
    }

    @Test
    public void selectTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);

        Integer bno = boardDao.selectAll().get(0).getBoard_no();
        boardDto.setBoard_no(bno);
        BoardDto boardDto2 = boardDao.select(bno);
        assertTrue(boardDto.equals(boardDto2));
    }

    @Test
    public void selectPageTest() throws Exception {
        boardDao.deleteAll();

        for (int i = 1; i <= 10; i++) {
            BoardDto boardDto = new BoardDto("title"+i, "content"+i, "asdf");
            boardDao.insert(boardDto);
            System.out.println("boardDto = " + boardDto);
        }

        Map map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 3);

        List<BoardDto> list = boardDao.selectPage(map);
        assertTrue(list.get(0).getBoard_title().equals("title10"));
        assertTrue(list.get(1).getBoard_title().equals("title9"));
        assertTrue(list.get(2).getBoard_title().equals("title8"));

        map = new HashMap();
        map.put("offset", 0);
        map.put("pageSize", 1);

        list = boardDao.selectPage(map);
        assertTrue(list.get(0).getBoard_title().equals("title10"));

        map = new HashMap();
        map.put("offset", 7);
        map.put("pageSize", 3);

        list = boardDao.selectPage(map);
        assertTrue(list.get(0).getBoard_title().equals("title3"));
        assertTrue(list.get(1).getBoard_title().equals("title2"));
        assertTrue(list.get(2).getBoard_title().equals("title1"));
    }

    @Test
    public void resultCntTest() throws Exception {
        boardDao.deleteAll();

    }

    @Test
    public void updateTest() throws Exception {
        boardDao.deleteAll();
        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);

        Integer bno = boardDao.selectAll().get(0).getBoard_no();
        System.out.println("bno = " + bno);
        boardDto.setBoard_no(bno);
        boardDto.setBoard_title("yes title");
        assertTrue(boardDao.update(boardDto)==1);

        BoardDto boardDto2 = boardDao.select(bno);
        assertTrue(boardDto.equals(boardDto2));
    }

    @Test
    public void increaseViewCntTest() throws Exception {
        boardDao.deleteAll();
        assertTrue(boardDao.count()==0);

        BoardDto boardDto = new BoardDto("title", "content", "asdf");
        assertTrue(boardDao.insert(boardDto)==1);
        assertTrue(boardDao.count()==1);

        Integer bno = boardDao.selectAll().get(0).getBoard_no();
        assertTrue(boardDao.increaseViewCnt(bno)==1);

        boardDto = boardDao.select(bno);
        assertTrue(boardDto!=null);
        assertTrue(boardDto.getBoard_view_cnt() == 1);

        assertTrue(boardDao.increaseViewCnt(bno)==1);
        boardDto = boardDao.select(bno);
        assertTrue(boardDto!=null);
        assertTrue(boardDto.getBoard_view_cnt() == 2);
    }
}