package com.fan.vturbo.example.netty.channelFuture;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Slf4j
public class ClientChannelFuture {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        ChannelFuture channelFuture = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder(StandardCharsets.UTF_8));
                        channel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                    }
                })
                .connect(new InetSocketAddress("localhost", 6666));
        // sync同步处理结果, channelFuture会开辟新线程处理接下来的运作，addListener
        Channel channel =channelFuture.sync().channel();
        log.debug("=====>"+channel);

        new Thread(()->{
            Scanner scanner=new Scanner(System.in);
            while (true){
                String line =scanner.nextLine();
                if ("q".endsWith(line)){
                    channel.close();
                    // 连接后之后就不阻塞了
                    break;
                }
                channel.writeAndFlush(line);
            }
        },"client input: ").start();

        // 用同步阻塞控制操作顺序
        // 方法一
        /*ChannelFuture closeFuture=channel.closeFuture();
        System.out.println("waiting close");
        closeFuture.sync();
        log.debug("处理输入线程关闭后的操作");*/

        // 方法二，使用通道线程处理
        ChannelFuture closeFuture=channel.closeFuture();
        closeFuture.addListener((ChannelFutureListener) channelFuture1 -> {
            // 执行关闭后的操作
            log.debug("处理输入线程关闭后的操作");
            group.shutdownGracefully();
        });
    }
}
