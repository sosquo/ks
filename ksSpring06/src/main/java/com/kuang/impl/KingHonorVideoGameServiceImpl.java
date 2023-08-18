package com.kuang.impl;

import com.kuang.service.VideoGameService;

public class KingHonorVideoGameServiceImpl implements VideoGameService {

    @Override
    public void play() {
        System.out.println("play 王者荣耀");
    }
}
