package com.kuang;

import com.kuang.impl.LolVideoGameServiceImpl;
import com.kuang.impl.LunchEatServiceImpl;
import com.kuang.proxy.EatServiceProxy;
import com.kuang.proxy.LiveHandler;
import com.kuang.service.EatService;
import com.kuang.service.VideoGameService;

public class Main {
    public static void main(String[] args) {
        staticProxy();
        System.out.println("--------------------");
        dynamicProxy();
        System.out.println("Hello world!");
    }

    private static void dynamicProxy() {
        VideoGameService target = new LolVideoGameServiceImpl();
        LiveHandler handler = new LiveHandler(target);
        VideoGameService instance = (VideoGameService)handler.getInstance();
        instance.play();
    }

    private static void staticProxy() {
//      EatService 抽象角色 、LunchEatServiceImpl 具体角色
        EatService eatService = new LunchEatServiceImpl();
//        代理角色
        EatServiceProxy proxy = new EatServiceProxy(eatService);
//        代理执行
        proxy.eat();
    }
}