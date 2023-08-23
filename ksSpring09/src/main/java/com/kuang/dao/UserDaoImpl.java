package com.kuang.dao;

import com.kuang.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserMapper {

    //增加一些操作
    public List<User> selectUser() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        return mapper.selectUser();
    }

    @Override
    public void pretendAddUser() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        String name = "小明";
        User user = new User(name, "123456");
        mapper.addUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        mapper.deleteUser(map);
    }

    @Override
    public void failedPretendAddUser() {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        String name = "小梦";
        User user = new User(name, "123456");
        mapper.addUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        mapper.deleteUser(map);
    }

    //新增
    public int addUser(User user) {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        return mapper.addUser(user);
    }

    //删除
    public int deleteUser(Map map) {
        UserMapper mapper = getSqlSession().getMapper(UserMapper.class);
        return mapper.deleteUser(map);
    }

}