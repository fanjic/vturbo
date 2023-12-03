package com.fan.vturbo.example;

import com.fan.vturbo.entity.info.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DoStream {
    public static void main(String[] args) {

        List l1 = Arrays.asList("a", "b", "c", "d", "e");
        List l2 = Arrays.asList("a", "b", "g", "f");
        // 如果有重复的元素，addAll会报错
        /*List l3 = new ArrayList();
        l3.addAll(l1);
        l3.addAll(l2);
        System.out.println(l3.stream().distinct().sorted().collect(Collectors.toList()));*/
        // 相当于循环l1的元素
        System.out.println(l1.stream().filter(l -> l2.contains(l)).collect(Collectors.toList()));
        System.out.println(l1.stream().filter(l -> !l2.contains(l)).collect(Collectors.toList()));


        System.out.println("\n" + "stream流====================");
        Person per1 = new Person(1, "步惊云", "男", 27, "无求意决");
        Person per2 = new Person(2, "聂风", "男", 24, "傲寒六决");
        Person per3 = new Person(3, "无名", "男", 40, "万剑归宗");
        Person per4 = new Person(4, "剑圣", "男", 56, "剑二十三");
        Person per5 = new Person(5, "慕应雄", "男", 41, "无天绝剑");
        Person per6 = new Person(6, "第二梦", "女", 25, "轻功");
        Person per7 = new Person(7, "紫凝", "女", 28, "独孤九剑");
        Person per8 = new Person(8, "赤雪", "女", 18, "赤火神功");
        List<Person> pers = new ArrayList<>();
        pers.add(per1);
        pers.add(per2);
        pers.add(per3);
        pers.add(per4);
        pers.add(per5);
        pers.add(per6);
        pers.add(per7);
        pers.add(per8);

        System.out.println(pers.stream().filter(x -> x.getAge() > 20).findFirst().get());
        System.out.println(pers.stream().limit(2).map(Person::getName).collect(Collectors.toList()));
        System.out.println(pers.stream().skip(5).map(Person::getName).collect(Collectors.toList()));
        System.out.println(pers.stream().max(Comparator.comparing(x -> x.getAge())).get());
        System.out.println(pers.stream().max(Comparator.comparing(Person::getAge)).get());
        System.out.println(pers.stream().sorted(Comparator.comparing(x -> x.getAge())).map(x -> x.getAge()).collect(Collectors.toList()));
        System.out.println(pers.stream().sorted(Comparator.comparing(x -> x.getAge())).map(Person::getAge).collect(Collectors.toList()));
        System.out.println(pers.stream().sorted(Comparator.comparing(Person::getAge)).map(Person::getAge).collect(Collectors.toList()));
        System.out.println(pers.stream().filter(x -> x.getSkill().contains("剑")).map(Person::getSkill).collect(Collectors.toList()));

        System.out.println(pers.stream().filter(x -> x.getAge() % 2 == 1).map(Person::getAge).collect(Collectors.toList()));
        System.out.println(pers.stream().filter(x -> x.getAge() > 20 && x.getAge() < 30).map(Person::getAge).collect(Collectors.toList()));
        System.out.println(pers.stream().filter(x -> x.getAge() > 40).map(Person::getAge).collect(Collectors.toList()));
        System.out.println(pers.stream().filter(x -> x.getAge() > 40).
                map(x -> {
                    x.setAge(x.getAge() + 100);
                    return x;
                }).map(Person::getAge).collect(Collectors.toList()));

        pers.stream().filter(x -> x.getAge() > 40).collect(Collectors.toList()).forEach(System.out::println);
        pers.stream().filter(x -> x.getAge() > 40).collect(Collectors.toList()).forEach(x -> System.out.println(x));
        pers.stream().filter(x -> x.getAge() > 40).collect(Collectors.toMap(Person::getName, x -> x)).
                forEach((k, v) -> System.out.println(k + "----->" + v));

        System.out.println(pers.stream().filter(x -> x.getAge() > 40).count());
        System.out.println(pers.stream().filter(x -> x.getAge() > 40).collect(Collectors.counting()));
        System.out.println(pers.stream().collect(Collectors.averagingInt(Person::getAge)));
        System.out.println(pers.stream().collect(Collectors.summingInt(Person::getAge)));
        System.out.println(pers.stream().collect(Collectors.summingInt(Person::getAge)));
        System.out.println(pers.stream().collect(Collectors.summarizingInt(Person::getAge)));
        System.out.println(pers.stream().collect(Collectors.summarizingInt(Person::getAge)).getSum());
        System.out.println(pers.stream().collect(Collectors.summarizingInt(Person::getAge)).getMin());

        System.out.println(pers.stream().map(Person::getAge).reduce(Integer::max).get());
        System.out.println(pers.stream().map(Person::getAge).reduce(Integer::sum).get());
        System.out.println(pers.stream().collect(Collectors.
                reducing(0, Person::getAge, (x, y) -> (x + y - 10))));

        String names = pers.stream().map(Person::getName).collect(Collectors.joining(","));
        System.out.println(names);

        pers.stream().collect(Collectors.partitioningBy(x -> x.getAge() > 30)).forEach((k, v) -> System.out.println(k + "----->" + v));

        /*Map<String, List<Person>> perMap = pers.stream().collect(Collectors.groupingBy(Person::getSex));
        perMap.forEach((k, v) -> System.out.println(k + "：" + v.stream().map(Person::getName).collect(Collectors.toList())));

        Map<Integer, List<Person>> perParentMap = pers.stream().collect(Collectors.groupingBy(Person::getParentId));
        pers.stream().peek(per->per.setPerChilds(perParentMap.get(per.getId()))).filter(per->per.getParentId()==0).collect(Collectors.toList())
                .forEach(x-> System.out.println(x));*/

    }
}
