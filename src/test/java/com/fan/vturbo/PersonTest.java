package com.fan.vturbo;

import com.fan.vturbo.dao.PersonDao;
import com.fan.vturbo.entity.info.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonTest {
    @Autowired
    private PersonDao personDao;

    @Test
    public void test() {
        Person per= personDao.getPer(1);
        System.out.println(per);
    }

}
