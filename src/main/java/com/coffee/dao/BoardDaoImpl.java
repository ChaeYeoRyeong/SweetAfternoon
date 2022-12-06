package com.coffee.dao;

import com.coffee.domain.BoardDto;
import com.coffee.domain.SearchCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public class BoardDaoImpl implements BoardDao {

    @Autowired
    private SqlSession session;

    private static String namespace = "com.coffee.dao.BoardDao."; // boardMapper.xml에 지정한 namespace

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public BoardDto select(Integer board_no) throws Exception { // boardMapper.xml에서 지정한 resultType인 BoardDto를 반환하는 메소드
        return session.selectOne(namespace + "select", board_no); // boardMapper.xml의 id, parameterType, resultType와 일치하게 작성
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace = "selectAll");
    }

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    }

    @Override
    public int insert(BoardDto dto) throws Exception{
        return session.insert(namespace + "insert", dto);
    }

    @Override
    public int update(BoardDto dto) throws Exception {
        return session.update(namespace + "update", dto);
    }

    @Override
    public int delete(Integer board_no, String board_writer) throws Exception {
        Map map = new HashMap();
        map.put("board_no", board_no);
        map.put("board_writer", board_writer);
        return session.delete(namespace+"delete", map);
    }

    @Override
    public int deleteAll() {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int increaseViewCnt(Integer board_no) throws Exception {
        return session.update(namespace + "increaseViewCnt", board_no);
    }

    @Override
    public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace + "searchSelectPage", sc);
    }

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        return session.selectOne(namespace + "searchResultCnt", sc);
    }
}
