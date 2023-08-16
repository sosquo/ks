package com.kuang.service.impl;

import com.kuang.dao.impl.UserDao;
import com.kuang.dao.impl.UserDaoOracleImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl {

    private String uid;
    private UserDao ud;

    public void setUserDao(UserDaoOracleImpl userDao) {
        this.ud = userDao;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "uid='" + uid + '\'' +
                ", ud=" + ud +
                '}';
    }
}
