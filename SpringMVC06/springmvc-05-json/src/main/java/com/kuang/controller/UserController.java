package com.kuang.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kuang.pojo.User;
import com.kuang.utils.JsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    /**
     * 返回的json乱码解决
     * 1、方案： produces = "application/json;charset=utf-8"
     * 2、在springmvc的配置文件上添加一段消息StringHttpMessageConverter转换配置
     *
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/json0")
//    @ResponseBody
    public String json0() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User("小明", 3, "男");
        String str = objectMapper.writeValueAsString(user);
        return str;
    }

    /**
     * 返回的json乱码解决方案： produces = "application/json;charset=utf-8"
     *
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/json1", produces = "application/json;charset=utf-8")
//    @ResponseBody
    public String json1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User("小明", 3, "男");
        String str = objectMapper.writeValueAsString(user);
        return str;
    }

    @RequestMapping("/json2")
    public String json2() throws JsonProcessingException {

        //创建一个jackson的对象映射器，用来解析数据
        ObjectMapper mapper = new ObjectMapper();
        //创建一个对象
        User user1 = new User("秦疆1号", 3, "男");
        User user2 = new User("秦疆2号", 3, "男");
        User user3 = new User("秦疆3号", 3, "男");
        User user4 = new User("秦疆4号", 3, "男");
        List<User> list = new ArrayList<User>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        //将我们的对象解析成为json格式
        String str = mapper.writeValueAsString(list);
        return str;
    }

    @RequestMapping("/json3")
    public String json3() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date();
        return mapper.writeValueAsString(date);
    }

    @RequestMapping("/json4_0")
    public String json4_0() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String result = format.format(date);
        return mapper.writeValueAsString(result);
    }

    @RequestMapping("/json4")
    public String json4() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //不使用时间戳的方式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*String result = format.format(date);
        return  mapper.writeValueAsString(result);*/
        mapper.setDateFormat(format);
        return mapper.writeValueAsString(date);
    }

    @RequestMapping("/json5")
    public String json5() {
        Date date = new Date();
        return JsonUtils.getJson(date);
    }

}
