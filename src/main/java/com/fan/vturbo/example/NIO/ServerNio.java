package com.fan.vturbo.example.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerNio {

    public static void main(String[] args) {
        try {
            System.out.println("-----服务端启动-----");
            ServerSocketChannel ssChannel = ServerSocketChannel.open();
            ssChannel.configureBlocking(false);
            ssChannel.bind(new InetSocketAddress(7777));

            Selector selector = Selector.open();
            ssChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select() > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey sk = it.next();
                    if (sk.isAcceptable()) {
                        SocketChannel socketChannel = ssChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } else if (sk.isReadable()) {
                        SocketChannel sChannel = (SocketChannel) sk.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len=sChannel.read(buffer))>0){// read()读取了多少字节
                            buffer.flip();
                            System.out.println(new String(buffer.array(),0,len));
                            buffer.clear();
                        }
                    }
                    it.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
