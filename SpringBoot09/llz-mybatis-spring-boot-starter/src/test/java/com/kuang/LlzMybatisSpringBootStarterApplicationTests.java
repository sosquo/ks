package com.kuang;

import com.kuang.dao.CityDao;
import com.kuang.pojo.City;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LlzMybatisSpringBootStarterApplication.class)
class LlzMybatisSpringBootStarterApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    CityDao cityDao;

    @Test
    public void testCity() {
        City city = cityDao.selectCityById(2);
        City city1 = cityDao.selectCityByName("上海");
        System.out.println("city = " + city);
        System.out.println("city1 = " + city1);
    }
}
