package com.fan.vturbo.example.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ClientNio {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 7777));
            socketChannel.configureBlocking(false);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("请说：");
                String msg = sc.nextLine();
                buffer.put(("小明：" + msg).getBytes());
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
