package com.kuang.mapper;

import com.kuang.pojo.City;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CityMapper {

    City selectCityById(@Param("id") Long id);

    @Select("SELECT * FROM CITY WHERE name = #{name}")
    City selectCityByName(@Param("name") String name);
}
