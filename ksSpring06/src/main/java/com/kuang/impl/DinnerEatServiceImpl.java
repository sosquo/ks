package com.kuang.impl;

import com.kuang.service.EatService;

public class DinnerEatServiceImpl implements EatService {

    @Override
    public void eat() {
        System.out.println("吃晚餐");
    }
}
