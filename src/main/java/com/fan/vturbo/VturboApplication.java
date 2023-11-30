package com.fan.vturbo;

import com.fan.vturbo.controller.PersonController;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.fan.vturbo.dao")
public class VturboApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(VturboApplication.class, args);
        // PersonController bean = (PersonController) ctx.getBean("personController");
        PersonController bean = ctx.getBean(PersonController.class);
        System.out.println("bean =====>" + bean);
    }

}
