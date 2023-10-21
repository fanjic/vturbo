package com.fan.vturbo.service;

public class SonOne extends Father {

    public void sayHello() {
        System.out.println(this.name);
        System.out.println(this.gender);

    }


}

class test {
    public static void main(String[] args) {

        Father father = new Father();
        father.sayName();
        System.out.println(father.gender);

        String a = "123";
        String b = "123";
        System.out.println(a.equals(b));
        System.out.println(a == b);

    }
}