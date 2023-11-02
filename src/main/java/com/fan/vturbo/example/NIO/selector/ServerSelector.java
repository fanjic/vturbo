package com.fan.vturbo.example.NIO.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class ServerSelector {
    public static void main(String[] args) throws IOException {
        // 创建selector，管理通道channel
        Selector selector = Selector.open();
        // 创建打开服务器通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置成非阻塞模式
        ssc.configureBlocking(false);
        // 设置服绑定端口
        ssc.bind(new InetSocketAddress(8888));
        System.out.println("ServerSocketChannel=====>" + ssc);
        /* SelectionKey管理通道的事件，例举四种事件
         * accept -接收到连接请求时触发
         * connect -客户端的，连接建立后触发
         * read -可读事件
         * write -可写事件
         * */
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        System.out.println("register key=====>" + sscKey);
        while (true) {
            // select()方法，没有事件发生时阻塞，有事件发生时才运行
            // 在事件未处理时，不会阻塞
            selector.select();
            // selectedKeys()方法获取选择器中所有的事件
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = it.next();
                System.out.println("key=====>" + sscKey);
                // 处理完需要移除事件
                it.remove();

                // 区分事件类型
                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = serverSocketChannel.accept();
                    System.out.println("socketChannel=====>" + sc);
                    sc.configureBlocking(false);

                    // 将buffer作为注册的参数附件传入
                    ByteBuffer buffer = ByteBuffer.allocate(8);

                    SelectionKey sckey = sc.register(selector, 0, buffer);
                    sckey.interestOps(SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 通过key拿到channel,此时的key就是可读事件
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        int read = channel.read(buffer);
                        if (read > 0){
                            // 开始读取数据
                            if(buffer.position()==buffer.limit()){
                                ByteBuffer newBuffer=ByteBuffer.allocate(buffer.capacity()*2);
                                buffer.flip();
                                newBuffer.put(buffer);
                                key.attach(newBuffer);

                            }

                        }
                    } catch (Exception e) {
                        // 防止阻塞，释放key
                        key.cancel();
                        e.printStackTrace();
                    }


                }
//                key.cancel();
            }

        }

    }
}
