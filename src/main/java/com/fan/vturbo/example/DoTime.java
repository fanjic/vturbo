package com.fan.vturbo.example;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

public class DoTime {
    public static void main(String[] args) throws IOException {

        System.out.println("传统base64加密====================");
        BASE64Encoder encoder=new BASE64Encoder();
        BASE64Decoder decoder=new BASE64Decoder();
        String name="范继成";
        byte[] bytes=name.getBytes(StandardCharsets.UTF_8);
        String encode=encoder.encode(bytes);
        System.out.println(encode);
        System.out.println(new String(decoder.decodeBuffer(encode)));
        System.out.println("JDK8之新增base64加密====================");
        Base64.Encoder encoder2=Base64.getEncoder();
        Base64.Decoder decoder2=Base64.getDecoder();
        String name2="范继成";
        byte[] bytes2=name.getBytes(StandardCharsets.UTF_8);
        String encode2=encoder2.encodeToString(bytes2);
        System.out.println(encode2);
        System.out.println(new String(decoder2.decode(encode2)));

        System.out.println("\n" + "时间类，往后三个月====================");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 3);
        //gettime()方法的返回值为date类型
        Date future = calendar.getTime();
        System.out.println(format.format(future));
        System.out.println("Calendar类set时间====================");
        calendar.set(Calendar.YEAR,2023);
        calendar.set(Calendar.MONTH,9);
        calendar.set(Calendar.DAY_OF_MONTH,26);
        System.out.println(format.format(calendar.getTime()));

        System.out.println("\n" + " JDK8之时间日期处理类====================");
        LocalDateTime localDateTime=LocalDateTime.now();
        LocalDateTime newTime=LocalDateTime.of(2022,10,26,23,46);
        System.out.println(localDateTime);
        System.out.println(newTime);
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter.format(localDateTime));



    }
}
