package com.kuang;

import com.kuang.mapperplus.CityMapperPlus;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SampleMybatisApplication implements CommandLineRunner {

    private final CityMapperPlus cityMapper;

    public SampleMybatisApplication(CityMapperPlus cityMapper) {
        this.cityMapper = cityMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleMybatisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.cityMapper.findByState("CA"));
    }

}
