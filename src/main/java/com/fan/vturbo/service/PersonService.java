package com.fan.vturbo.service;

import com.fan.vturbo.entity.info.Person;

import java.util.List;

public interface PersonService {

    Person getPer(Integer id);


    List<Person> getPers();
}
