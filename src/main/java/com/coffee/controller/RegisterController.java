package com.coffee.controller;

import com.coffee.dao.UserDao;
import com.coffee.domain.UserDto;
import com.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserDao userDao;

    @InitBinder
    public void toDate(WebDataBinder binder) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
    }

    final int FAIL = 0;

    @GetMapping("/add")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/add")
    public String save(UserDto user, BindingResult result, Model m) throws Exception {
        System.out.println("result="+result);
        System.out.println("user="+user);

        // DB에 새로 가입한 회원 정보 저장
        int rowCnt = userDao.insertUser(user);
        if(rowCnt!=FAIL) { // 저장 결과가 성공이면 홈 화면으로 이동
            return "redirect:/home";
        }
        // 저장 실패하면 registerForm으로 이동
        return "registerForm";
    }

    // 아이디 중복 검사
    @PostMapping("/idCheck.do")
    @ResponseBody
    public int idCheck(@RequestBody String user_id) throws Exception{
        int result = userDao.idCheck(user_id);
        return result;
    }
}

