package com.fan.vturbo.domain;


import com.fan.vturbo.example.Father;
import com.fan.vturbo.example.SonOne;

public class SonTwo extends Father {

    public void sayHello(){
        System.out.println(this.name);
//        System.out.println(this.gender); default跨包//这里所谓不同包跨包指的是package
        System.out.println(this.height); //protect跨包
    }

    public static void main(String[] args) {

        Father father = new Father();
        father.sayName();
//        System.out.println(father.gender); //default跨包
//        System.out.println(father.height); //protect跨包
        SonOne sonOne = new SonOne();
        sonOne.sayHello();
    }
}
