package com.apktool.netty.client;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    private Channel connect(EventLoopGroup group) throws InterruptedException {
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new LoggingHandler(LogLevel.INFO));
                        p.addLast(new EchoClientHandler());
                    }
                });

        return b.connect(HOST, PORT)
                .addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture channelFuture) throws Exception {
                        if (channelFuture.isSuccess()) {
                            log.info("连接Netty服务端成功...");
                        } else {
                            log.info("连接Netty服务端失败，进行断线重连...");
                            final EventLoop loop = channelFuture.channel().eventLoop();
                            loop.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    log.info("连接正在重试...");
                                    try {
                                        connect(group);
                                    } catch (InterruptedException e) {
                                        log.error(e.getMessage());
                                    }
                                }
                            }, 20, TimeUnit.SECONDS);
                        }
                    }
                })
                .sync().channel();
    }


    public void run() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Channel channel = connect(group);

        String data = "Hello world";
        String res = new MessageHandler().sendSyncMsg(channel, data);
        System.out.println(res);

        // Wait until the connection is closed.
        channel.closeFuture().sync();
        // Shut down the event loop to terminate all threads.
        group.shutdownGracefully();
    }

    public static void main(String[] args) throws Exception {
        EchoClient client = new EchoClient();
        client.run();
    }
}