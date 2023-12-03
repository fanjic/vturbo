package com.fan.vturbo.dao;

import com.fan.vturbo.entity.info.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonDao {

    @Select("select *from person where id =#{id}")
    Person getPer(Integer id);

    @Select("select *from person")
    List<Person> getPers();
}
