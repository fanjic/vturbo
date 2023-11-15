package com.fan.vturbo.example.Netty.eventLoop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class ServerEventLoop {
    public static void main(String[] args) {
        EventLoopGroup newGroup = new DefaultEventLoopGroup();

        new ServerBootstrap()
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        channel.pipeline().addLast("handler1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext handlerContext, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.debug(buf.toString(StandardCharsets.UTF_8));
                                // super.channelRead(handlerContext,msg); 等同fireChannelRead()
                                // 把消息(本个方法得到的结果)传给下一个handler，如果不调用。handler调用链就会断开
                                handlerContext.fireChannelRead(msg);
                            }
                            // EmbeddedChannel测试多个handler的入栈和出栈
                        }).addLast(newGroup, "handler2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext channelHandlerContext, Object msg) throws Exception {
                                ByteBuf buf = (ByteBuf) msg;
                                log.debug(buf.toString(StandardCharsets.UTF_8));
                            }
                        });
                    }
                })
                .bind(6666);
    }
}
