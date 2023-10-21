package com.fan.vturbo.entity.info;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 范小城
 * @since 2022-06-25
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private String skill;

    private Date createTime;

    private Date updateTime;

    private Integer parentId;

    private List<PersonInfo> perChilds;

    public PersonInfo(Integer id, String name, String sex, Integer age, String skill, Integer parentId) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.skill = skill;
        this.parentId = parentId;
    }
}
