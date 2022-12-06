package com.coffee.dao;

import com.coffee.domain.RecommendCoffeeDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecommendCoffeeDaoImpl implements RecommendCoffeeDao {
    @Autowired
    SqlSession session;

    private static String namespace = "com.coffee.dao.CoffeeDao.";

    public List<RecommendCoffeeDto> select(Integer radio1, Integer radio2, Integer radio3) {
        Map map = new HashMap();
        map.put("radio1", radio1);
        map.put("radio2", radio2);
        map.put("radio3", radio3);
        return session.selectList(namespace + "select", map);

    }
}
