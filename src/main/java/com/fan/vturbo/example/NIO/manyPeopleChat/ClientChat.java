package com.fan.vturbo.example.NIO.manyPeopleChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class ClientChat {

    private Selector selector;
    private SocketChannel socketChannel;
    private static final int port = 7777;

    public ClientChat(){
        try {
            // 创造选择器
            selector=Selector.open();
            // 连接服务器
            socketChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",port));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("客户端准备完成");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInfo() {
        try {
            if(selector.select()>0){
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()){
                    SelectionKey sk=it.next();
                    if(sk.isReadable()){
                        // 获取相关通道
                        SocketChannel sc=(SocketChannel)sk.channel();
                        ByteBuffer buffer=ByteBuffer.allocate(1024);
                        //读取
                        sc.read(buffer);
                        //把读到的缓冲区的数据转成字符串
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg){
        try {
            // 一步到位
            socketChannel.write(ByteBuffer.wrap(("小明说："+msg).getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientChat clientChat=new ClientChat();
        new Thread(new Runnable() {
            @Override
            public void run() {
                clientChat.readInfo();
            }
        }).start();

        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            String msg=sc.nextLine();
            clientChat.sendMsg(msg);
        }
    }

}
