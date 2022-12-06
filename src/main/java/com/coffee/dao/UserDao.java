package com.coffee.dao;

import com.coffee.domain.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserDao {
    int count() throws Exception;

    UserDto selectUser(String user_id) throws Exception;

    int deleteUser(String user_id, String user_pwd) throws Exception;

    int deleteAll() throws Exception;

    int insertUser(UserDto user) throws Exception;

    int updateUser(UserDto user) throws Exception;

    int idCheck(String user_id) throws Exception;
}
