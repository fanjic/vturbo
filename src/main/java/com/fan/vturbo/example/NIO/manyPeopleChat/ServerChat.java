package com.fan.vturbo.example.NIO.manyPeopleChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class ServerChat {

    // 定义成员属性，选择器、服务器通道、端口
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int port = 7777;

    // 构造器初始化对象
    public ServerChat() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.bind(new InetSocketAddress(7777));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (selector.select() > 0) {
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {

                    // SelectionKey管道中的事件，接入事件、读取事件
                    SelectionKey sk = it.next();
                    if (sk.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        // 注册给服务器，监听读事件
                        socketChannel.register(selector, SelectionKey.OP_READ);

                    } else if (sk.isReadable()) {
                        // 处理客户端的消息
                        readClientData(sk);
                    }
                    // 处理完移除事件
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 处理客户端的消息逻辑
    private void readClientData(SelectionKey sk) {
        // 客户端通道都SocketChannel
        SocketChannel socketChannel = null;
        try {
            socketChannel = (SocketChannel) sk.channel();
            // 创建数据缓冲区，接收客户端数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = socketChannel.read(buffer);
            if (count > 0) {
                buffer.flip();
                String msg = new String(buffer.array(), 0, buffer.remaining());
                System.out.println("接收到的客户端消息：" + msg);

                sendMsgToAllClient(msg, socketChannel);
            }
        } catch (Exception e) {
            try {
                System.out.println("有人下线了：" + socketChannel.getRemoteAddress());
                sk.cancel();
                socketChannel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    // 把当前消息推给在线人
    private void sendMsgToAllClient(String msg, SocketChannel selfChannel)  {
        System.out.println("服务器开始转发消息，当前线程：" + Thread.currentThread().getName());
        try {
            for (SelectionKey key : selector.keys()) {// 获取所有通道
                // 排除自己的通道
                Channel channel = key.channel();
                if (channel instanceof SocketChannel && channel != selfChannel){
                    // 将msg写入buffer
                    ByteBuffer buffer=ByteBuffer.wrap(msg.getBytes());
                    ((SocketChannel)channel).write(buffer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("服务端开始启动，群聊开始");
        ServerChat serverChat = new ServerChat();
        // 开始监听
        serverChat.listen();
    }

}
