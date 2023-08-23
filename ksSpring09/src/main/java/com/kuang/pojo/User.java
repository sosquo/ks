package com.kuang.pojo;

public class User {
    private int id;  //id
    private String name;   //姓名
    private String pwd;   //密码

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}