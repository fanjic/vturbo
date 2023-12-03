package com.fan.vturbo.service.Impl;

import com.fan.vturbo.dao.PersonDao;
import com.fan.vturbo.entity.info.Person;
import com.fan.vturbo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl  implements PersonService {
    // 将数据放入缓存，方案之一
    // private HashMap<Integer, Person> cache=new HashMap<>();

    @Autowired
    private PersonDao personDao;

    @Override
    @Cacheable(value = "cacheSpace", key = "#id")
    public Person getPer(Integer id) {
        return personDao.getPer(id);
    }

    @Override
    public List<Person> getPers() {
        return personDao.getPers();
    }
}
