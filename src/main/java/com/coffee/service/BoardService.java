package com.coffee.service;

import com.coffee.domain.BoardDto;
import com.coffee.domain.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Service
public interface BoardService {
    // BoardDao의 count()
    int getCount() throws Exception;

    // BoardDao의 select(), increaseViewCnt() -> 게시글 읽으면 조회수 올라가야함
    BoardDto read(Integer board_no) throws Exception;

    // BoardDao의 selectAll()
    List<BoardDto> getList() throws Exception;

    // BoardDao의 selectPage()
    List<BoardDto> getPage(Map map) throws Exception;

    // BoardDao의 insert()
    int write(BoardDto boardDto) throws Exception;

    // BoardDao의 update()
    int modify(BoardDto boardDto) throws Exception;

    // BoardDao의 delete()
    int remove(Integer board_no, String board_writer) throws Exception;

    // BoardDao의 searchResultPage()
    List<BoardDto> searchResultPage(SearchCondition sc) throws Exception;

    // BoardDao의 searchResultCnt()
    int searchResultCnt(SearchCondition sc) throws Exception;
}
