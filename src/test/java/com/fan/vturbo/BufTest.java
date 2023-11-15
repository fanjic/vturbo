package com.fan.vturbo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

public class BufTest {

    @Test
    public void test1() {
        ByteBuf buf= ByteBufAllocator.DEFAULT.buffer(5);
        System.out.println(buf);

        String str="abcde";
        buf.writeBytes(str.getBytes());
        System.out.println(buf.toString(Charset.defaultCharset()));

        ByteBuf buf1=buf.slice(0,3);
        ByteBuf buf2=buf.slice(3,2);
        System.out.println("buf1----->"+buf1.toString(Charset.defaultCharset()));
        System.out.println("buf2----->"+buf2.toString(Charset.defaultCharset()));
        // release()释放内存

        ByteBuf buf3=buf.duplicate();
        // 零拷贝不复制内存内容，指引用
        // copy()深拷贝
        System.out.println("duplicate----->"+buf3.toString(Charset.defaultCharset()));


    }


}
