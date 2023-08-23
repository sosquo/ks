package com.kuang.dao;

import com.kuang.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> selectUser();

    void pretendAddUser();

    void failedPretendAddUser();

    int addUser(User user);

    int deleteUser(Map map);
}
