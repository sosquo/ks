package com.kuang.impl;

import com.kuang.service.VideoGameService;

public class LolVideoGameServiceImpl implements VideoGameService {

    @Override
    public void play() {
        System.out.println("play 英雄联盟");
    }
}
