package com.fan.vturbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fan")
public class TestController {

    @RequestMapping("/say")
    public String say(){
        return "hello";
    }
}
