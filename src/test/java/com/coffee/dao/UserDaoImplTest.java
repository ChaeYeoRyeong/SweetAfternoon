package com.coffee.dao;

import com.coffee.domain.UserDto;
import com.coffee.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@WebAppConfiguration
@SpringBootTest
class UserDaoImplTest {

    @Autowired
    private UserService userService;

    @Test
    void idCheck() throws Exception{
        String user_id = "rlacl";
        int rowCnt = userService.idCheck(user_id);
        assertTrue(rowCnt == 1);
    }


//    @Test
//    void updateUser() throws Exception {
//        userDao.deleteUser("aaaaa", "1234");
////        UserDto user= new UserDto("aaaaa", "1234", "김볶밥", "kbb@naver.com", new Date());
//        int rowCnt = userDao.insertUser(user);
//        assertTrue(rowCnt == 1);
//
//        user.setUser_pwd("123123");
//        user.setUser_email("abc@gmail.com");
//        rowCnt = userDao.updateUser(user);
//        assertTrue(rowCnt == 1);
//
//        UserDto user2 = userDao.selectUser(user.getUser_id());
//        assertTrue(user.equals(user2));
//    }
}