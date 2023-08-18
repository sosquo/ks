package com.kuang.impl;

import com.kuang.service.EatService;

public class LunchEatServiceImpl implements EatService {

    @Override
    public void eat() {
        System.out.println("吃午餐");
    }
}
