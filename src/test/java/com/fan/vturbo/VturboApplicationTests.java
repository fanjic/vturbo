package com.fan.vturbo;

import com.fan.vturbo.dao.PersonDao;
import com.fan.vturbo.entity.info.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class VturboApplicationTests {
    @Autowired
    private PersonDao personDao;

    @Test
    void contextLoads() {
        AtomicInteger num=new AtomicInteger(0);
        System.out.println(num.getAndIncrement());
        System.out.println(num);
        System.out.println(num.incrementAndGet());
    }

    @Test
    public void test1() {

    }


}
