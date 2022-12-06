package com.coffee.service;

import com.coffee.dao.UserDao;
import com.coffee.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    // UserDao의 count()
    @Override
    public int getCount() throws Exception {
        return userDao.count();
    }

    // UserDao의 selectUser()
    @Override
    public UserDto read(String user_id) throws Exception {
        return userDao.selectUser(user_id);
    }

    // UserDao의 deleteUser()
    @Override
    public int remove(String user_id, String user_pwd) throws Exception {
        return userDao.deleteUser(user_id, user_pwd);
    }

    // UserDao의 insert()
    @Override
    public int write(UserDto userDto) throws Exception {
        return userDao.insertUser(userDto);
    }

    // UserDao의 update()
    @Override
    public int modify(UserDto userDto) throws Exception {
        return userDao.updateUser(userDto);
    }

    // UserDao의 idCheck()
    public int idCheck(String user_id) throws Exception{
        return userDao.idCheck(user_id);
    }


}
