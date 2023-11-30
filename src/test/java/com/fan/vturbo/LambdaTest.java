package com.fan.vturbo;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaTest {

    List<String> fruit = Arrays.asList("apple", "orange", "banana", "grape", "pear");
    List<Integer> nums = Arrays.asList(1, 3, 5, 4, 2);

    @Test
    public void get() {
        // pers.forEach(System.out::println);
        // 初始写法，可以省略{}
        fruit.forEach(fru -> {
            System.out.println(fru);
        });
        System.out.println("筛选=====>");

        List<String> someFruit = fruit.stream().filter(fru -> fru.endsWith("e")).collect(Collectors.toList());
        someFruit.forEach(fru -> {
            System.out.println(fru);
        });
    }

    @Test
    public void sort() {
        /*Collections.sort(nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return a.compareTo(b);
            }
        });*/
        nums.forEach(System.out::println);

        Collections.sort(nums, (a, b) -> a.compareTo(b));
        System.out.println("排序=====>");
        nums.forEach(System.out::println);

        // identify初始值
        int count = nums.stream().reduce(0, (a, b) -> a + b);
        System.out.println("累加== " + count);
    }

    @Test
    public void group() {
        Map<Integer, List<String>> group = new HashMap<>();
        for (String fru : fruit) {
            int len = fru.length();
            if (!group.containsKey(len)) {
                group.put(len, new ArrayList());
            }
            group.get(len).add(fru);
        }
        System.out.println(group);
        group.forEach((k, v) -> System.out.println("长度为" + k + "的有：" + v));

        // group = fruit.stream().collect(Collectors.groupingBy(String::length));
        Map<Integer, List<String>> newGroup = fruit.stream().collect(Collectors.groupingBy(fru->fru.length()));
        System.out.println("重新分组=====>");
        newGroup.forEach((k, v) -> System.out.println("长度为" + k + "的有：" + v));

    }

}
