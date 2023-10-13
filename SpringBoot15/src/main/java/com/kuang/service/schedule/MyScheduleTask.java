package com.kuang.service.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MyScheduleTask {

    @Scheduled(cron = "0/5 * * * * *")
    public void scheduledCleanLog() {
        System.out.println("清理日志");
    }
}
