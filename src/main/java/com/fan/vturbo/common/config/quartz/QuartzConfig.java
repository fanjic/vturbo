package com.fan.vturbo.common.config.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 被注解的类内部包含有一个或多个被@Bean注解的方法
@Configuration
public class QuartzConfig {

    // @Bean是用在方法上，将当前方法的返回值对象放到容器当中
    // 可以理解为前者是由spring自动创建对象，而@Bean创建对象是交给我们自己来控制
    @Bean
    public JobDetail setJobDetail() {
        return JobBuilder
                .newJob(QuartzJob.class)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger setJobTrigger() {
        /*ScheduleBuilder scheduler = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder
                .newTrigger()
                .forJob(setJobDetail())
                .withSchedule(scheduler)
                .build();*/
        return null;
    }


}
