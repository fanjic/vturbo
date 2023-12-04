package com.fan.vturbo.common.config.quartz;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskBean {

    @Scheduled(cron = "0/5 * * * * ?")
    public void doTask(){
        // 启动类@EnableScheduling，yml需要配置
        System.out.println("spring task doing");
    }

}
