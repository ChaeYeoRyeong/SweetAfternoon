package com.coffee.service;

import com.coffee.dao.RecommendCoffeeDao;
import com.coffee.domain.RecommendCoffeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendCoffeeServiceImpl implements RecommendCoffeeService {
    @Autowired
    RecommendCoffeeDao coffeeDao;

    public List<RecommendCoffeeDto> getCoffee(Integer radio1, Integer radio2, Integer radio3) throws Exception{
        return coffeeDao.select(radio1, radio2, radio3);
    }
}
