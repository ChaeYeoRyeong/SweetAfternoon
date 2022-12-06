package com.coffee.service;

import com.coffee.domain.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    // UserDao의 count()
    int getCount() throws Exception;

    // UserDao의 selectUser()
    UserDto read(String user_id) throws Exception;

    // UserDao의 deleteUser()
    int remove(String user_id, String user_pwd) throws Exception;

    // UserDao의 insert()
    int write(UserDto userDto) throws Exception;

    // UserDao의 update()
    int modify(UserDto userDto) throws Exception;

    // UserDao의 idCheck()
    int idCheck(String user_id) throws Exception;
}
