package com.kuang.pojo;

public class UserT {

    private String name;

    public UserT() {
    }

    public UserT(String name) {
        System.out.println("构造方法 -- name = " + name);
        this.name = name;
    }

    public void setName(String name) {
        System.out.println("setName方法注入 -- name = " + name);
        this.name = name;
    }

    public void show(){
        System.out.println("zhucm_test_show_name = "+ name );
    }

}