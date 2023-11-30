package com.fan.vturbo.entity.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Integer id;

    private String name;

    private String gender;

    private Integer age;

    private String skill;

    private String des;

    public Person(Integer id, String name, String gender, Integer age, String skill) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.skill = skill;
    }
}
