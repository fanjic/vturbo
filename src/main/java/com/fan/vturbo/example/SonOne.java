package com.fan.vturbo.example;

import java.io.File;

public class SonOne extends Father {

    public void sayHello() {
        System.out.println(this.name);
        System.out.println(this.gender);

    }

    public static void main(String[] args) {
        Father father = new Father();
        father.sayName();
        System.out.println(father.gender);

        String a = "123";
        String b = "123";
        System.out.println(a.equals(b));
        System.out.println(a == b);

        String str="0.12.345";
        System.out.println(str.lastIndexOf("."));
        System.out.println(str.substring(4));

        String suffix = ".txt";
        File file = new File("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\hello.txt");
        if(file.exists()){
            System.out.println(file.getName());
            String fileName= file.getName();
            suffix=fileName.substring(fileName.lastIndexOf("."));
            System.out.println("客户端截取的文件后缀："+suffix);
        }
    }

}
