package com.fan.vturbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${wukong.wufan}")
    private String daughter;

    @Autowired
    private Environment environment;

    // http://start.aliyun.com
    @RequestMapping("/got")
    public String say(){
        System.out.println(daughter);
        System.out.println(environment.getProperty("wukong.wufan"));
        return daughter;
    }

}
