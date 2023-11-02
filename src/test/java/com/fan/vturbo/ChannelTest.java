package com.fan.vturbo;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

public class ChannelTest {

    @Test
    public void read() {
        try {
            File file = new File("channelFile\\a.txt");
            FileInputStream fis = new FileInputStream(file);
            FileChannel channel = fis.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            System.out.println(new String(buffer.array(), 0, buffer.remaining()));// 转化为字节数组
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void write() {
        File file = new File("channelFile\\a.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            FileChannel channel = fos.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello java".getBytes());
            buffer.flip();

            channel.write(buffer);
            channel.close();
            System.out.println("文件中已经添加内容");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void copy() {

        File srcFile = new File("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\10.jpg");
        String fileName = "";
        if (srcFile.exists()) {
            fileName = srcFile.getName();
            System.out.println(srcFile.getName());
        }
        File desFile = new File("C:\\Users\\Administrator\\Desktop\\do\\file\\BIO\\copynew\\" + fileName);

        try {
            // 文件输入输出流
            FileInputStream is = new FileInputStream(srcFile);
            FileOutputStream os = new FileOutputStream(desFile);
            // 文件通道
            FileChannel isChannel = is.getChannel();
            FileChannel osChannel = os.getChannel();
            // 缓存区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                // 再次读写的时候应该清空
                buffer.clear();

                int flag = isChannel.read(buffer);
                if (flag == -1) {
                    break;
                }
                buffer.flip();
                osChannel.write(buffer);
            }
            isChannel.close();
            osChannel.close();
            System.out.println("文件复制完成");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fenSan() {

        File file = new File("channelFile\\a.txt");
        try {
            FileInputStream is = new FileInputStream(file);
            FileChannel isChannel = is.getChannel();

            ByteBuffer buffer1 = ByteBuffer.allocate(4);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            ByteBuffer[] buffers = {buffer1, buffer2};
            isChannel.read(buffers);
            for (ByteBuffer bu : buffers) {
                bu.flip();
                System.out.println(new String(bu.array(), 0, bu.remaining()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
