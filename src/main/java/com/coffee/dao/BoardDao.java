package com.coffee.dao;

import com.coffee.domain.BoardDto;
import com.coffee.domain.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface BoardDao {
    int count() throws Exception;

    BoardDto select(Integer board_no) throws Exception;

    List<BoardDto> selectAll() throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;

    int insert(BoardDto dto) throws Exception;

    int update(BoardDto dto) throws Exception;

    int delete(Integer board_no, String board_writer) throws Exception;

    int deleteAll();

    int increaseViewCnt(Integer board_no) throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;

    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;
}
