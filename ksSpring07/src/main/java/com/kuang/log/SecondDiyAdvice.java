package com.kuang.log;

public class SecondDiyAdvice {

    public void myBefore() {
        System.out.println("------ 方法执行前 -------------");
    }

    public void myAfter() {
        System.out.println(" -------- 方法执行后  ------------- ");
    }
}
