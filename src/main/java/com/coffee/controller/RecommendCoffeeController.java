package com.coffee.controller;

import com.coffee.domain.RecommendCoffeeDto;
import com.coffee.service.RecommendCoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/coffee")
public class RecommendCoffeeController {

    @Autowired RecommendCoffeeService recommendCoffeeService;

    @GetMapping("/recommend")
    public String recommend() {
        return "recommendCoffee";
    }

    @GetMapping("/result")
    public String result(Model m, Integer radio1, Integer radio2, Integer radio3, String coffeeImg) throws Exception {
//        // radio버튼의 name과 value가 제대로 들어오는지 확인하기 위한 코드
//        List<String> radio = new ArrayList<String>();
//        Enumeration<String> names = request.getParameterNames();
//        do {
//            String name = names.nextElement();
//            String value = request.getParameter(name);
//
//            if(name.startsWith("radio")) {
//                radio.add(value);
//            }
//            System.out.print(name + "  ");
//            System.out.println(value);
//        } while(names.hasMoreElements());

        try {
            List<RecommendCoffeeDto> list = recommendCoffeeService.getCoffee(radio1, radio2, radio3);
            m.addAttribute("list", list);
            System.out.println("list = " + list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "recommendResult";
    }
}
