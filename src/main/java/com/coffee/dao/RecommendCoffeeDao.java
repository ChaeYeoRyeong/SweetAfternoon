package com.coffee.dao;

import com.coffee.domain.RecommendCoffeeDto;

import java.util.List;
import java.util.Map;

public interface RecommendCoffeeDao {
    List<RecommendCoffeeDto> select(Integer radio1, Integer radio2, Integer radio3) throws Exception;
}
