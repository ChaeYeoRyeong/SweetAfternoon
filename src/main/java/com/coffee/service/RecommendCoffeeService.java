package com.coffee.service;

import com.coffee.domain.RecommendCoffeeDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
@Service
public interface RecommendCoffeeService {
    List<RecommendCoffeeDto> getCoffee(Integer radio1, Integer radio2, Integer radio3) throws Exception;
}
