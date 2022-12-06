package com.coffee.dao;

import com.coffee.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public class UserDaoImpl implements UserDao {

    @Autowired
    SqlSession session;

    private static String namespace = "com.coffee.dao.UserDao.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public UserDto selectUser(String user_id) throws Exception {
        return session.selectOne(namespace + "selectUser", user_id);
    }

    @Override
    public int deleteUser(String user_id, String user_pwd) throws Exception {
        Map map = new HashMap();
        map.put("user_id", user_id);
        map.put("user_pwd", user_pwd);
        return session.delete(namespace + "deleteUser", map);
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int insertUser(UserDto user) throws Exception {
        return session.insert(namespace + "insertUser", user);
    }

    @Override
    public int updateUser(UserDto user) throws Exception {
        return session.update(namespace + "updateUser", user);
    }

    @Override
    public int idCheck(String user_id) throws Exception {
        return session.selectOne(namespace + "idCheck", user_id);
    }
}