package com.fan.vturbo;

import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;

public class BufferTest {

    @Test
    public void test() {

        ByteBuffer buffer=ByteBuffer.allocate(8);
        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());

        String str="abcdefg";
        buffer.put(str.getBytes());
        System.out.println(buffer.position()); //获取位置下标，从0开始
        System.out.println(buffer.limit());

        buffer.flip(); //可读模式
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println((char) buffer.get());// get()直接获取的是ascll码

        /*buffer.clear();
        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());// 并没清除数据，添加数据时覆盖*/

        // System.out.println((char) buffer.get());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());

        byte[] bt1=new byte[2];
        buffer.get(bt1);
        System.out.println(new String(bt1));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());

        buffer.mark(); //标记当前位置,下标3,d
        byte[] bt2=new byte[3];
        buffer.get(bt2);
        System.out.println(new String(bt2));
        System.out.println(buffer.position());
        System.out.println(buffer.limit());

        buffer.reset();
        System.out.println(buffer.position());
        if(buffer.hasRemaining()){
            System.out.println(buffer.remaining());// remaining获取元素个数
        }

    }

    @Test
    public void flip() {
        ByteBuffer buffer=ByteBuffer.allocate(8);
        System.out.println(buffer.position());
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());

        String str="abcdefg";
        buffer.put(str.getBytes());
        System.out.println(buffer.position()); //获取位置下标，从0开始
        System.out.println(buffer.limit());

        buffer.flip();
        System.out.println(buffer.position()); //获取位置下标，从0开始
        System.out.println(buffer.limit());

        byte[] bt1=new byte[2];
        buffer.get(bt1);
        System.out.println(new String(bt1));
        System.out.println(buffer.position());

        buffer.flip();
        System.out.println(buffer.position());

    }

}
