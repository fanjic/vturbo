package com.fan.vturbo.controller;

import com.fan.vturbo.dao.PersonDao;
import com.fan.vturbo.entity.info.Person;
import com.fan.vturbo.service.Impl.PersonServiceImpl;
import com.fan.vturbo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonService personService;

    @RequestMapping("/say")
    public String say(){
        System.out.println("hello");

        return "hello";
    }

    @RequestMapping("/get/{id}")
    public String getPer(@PathVariable Integer id){
        System.out.println(id);
        Person per= personService.getPer(id);
        System.out.println(per);
        return per.getName();
    }

    @RequestMapping("/getPers")
    public List<Person> getPer(){
        List<Person> pers= personService.getPers();
        List<Person> somePer = pers.stream().filter(p->p.getName().contains("悟")).collect(Collectors.toList());
        System.out.println(somePer);
        return somePer;
    }

}
