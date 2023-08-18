package com.kuang.proxy;

import com.kuang.service.EatService;

public class EatServiceProxy implements EatService{

    private EatService eatService;

    public EatServiceProxy(EatService eatService) {
        this.eatService = eatService;
    }

    @Override
    public void eat() {
        cookie();
        eatService.eat();
        washDishes();
    }

    public void cookie() {
        System.out.println("煮饭。。。。。。");
    }

    private void washDishes() {
        System.out.println("洗盘子。。。。。。。");
    }
}
