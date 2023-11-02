package com.fan.vturbo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class VturboApplicationTests {

    @Test
    void contextLoads() {
        AtomicInteger num=new AtomicInteger(0);
        System.out.println(num.getAndIncrement());
        System.out.println(num);
        System.out.println(num.incrementAndGet());

    }

}
