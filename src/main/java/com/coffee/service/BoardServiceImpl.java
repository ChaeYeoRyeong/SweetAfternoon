package com.coffee.service;

import com.coffee.dao.BoardDao;
import com.coffee.domain.BoardDto;
import com.coffee.domain.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardDao boardDao;

    // BoardDao의 searchResultPage()
    @Override
    public List<BoardDto> searchResultPage(SearchCondition sc) throws Exception {
        return boardDao.searchSelectPage(sc);
    }

    // BoardDao의 searchResultCnt()
    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        return boardDao.searchResultCnt(sc);
    }

    // BoardDao의 count()
    @Override
    public int getCount() throws Exception {
        return boardDao.count();
    }

    // BoardDao의 select(), increaseViewCnt() -> 게시글 읽으면 조회수 올라가야함
    @Override
    public BoardDto read(Integer board_no) throws Exception {
        BoardDto boardDto = boardDao.select(board_no);
        boardDao.increaseViewCnt(board_no);
        return boardDto;
    }

    // BoardDao의 selectAll()
    @Override
    public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }

    // BoardDao의 selectPage()
    @Override
    public List<BoardDto> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }

    // BoardDao의 insert()
    @Override
    public int write(BoardDto boardDto) throws Exception {
        return boardDao.insert(boardDto);
    }

    // BoardDao의 update()
    @Override
    public int modify(BoardDto boardDto) throws Exception{
        return boardDao.update(boardDto);
    }

    // BoardDao의 delete()
    @Override
    public int remove(Integer board_no, String board_writer) throws Exception {
        return boardDao.delete(board_no, board_writer);
    }
}
