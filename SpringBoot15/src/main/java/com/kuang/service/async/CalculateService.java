package com.kuang.service.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    /**
     * 告诉Spring这是一个异步方法
     * @param taskName
     */
    @Async
    public void calculatePlenty(String taskName) {
        System.out.println("服务层开始 -- taskName = " + taskName);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("服务层结束 -- taskName = " + taskName);
    }
}
