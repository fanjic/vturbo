package com.fan.vturbo.controller;

import com.fan.vturbo.dao.PersonDao;
import com.fan.vturbo.entity.info.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @RequestMapping("/say/{id}")
    public String getPer(@PathVariable Integer id){
        System.out.println(id);
        Person per= personDao.getPer(id);
        System.out.println(per);
        return per.getName();
    }


}
