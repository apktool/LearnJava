package com.apktool.rpc.client;

import java.util.concurrent.TimeUnit;

import com.apktool.protobuf.MessageProtos;
import com.apktool.rpc.common.MessageController;
import com.google.protobuf.BlockingRpcChannel;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;

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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoClient {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static Channel connect(EventLoopGroup group) throws InterruptedException {
        // Configure the client.
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

        // Start the client.
        ChannelFuture f = b.connect(HOST, PORT)
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
                .sync();
        Channel channel = f.channel();
        return channel;
    }

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Channel channel = connect(group);
            BlockingRpcChannel rpcChannel = new BlockingChannelImpl(channel);
            MessageProtos.AdminService.BlockingInterface stub = MessageProtos.AdminService.newBlockingStub(rpcChannel);

            MessageProtos.Request request = MessageProtos.Request.newBuilder().setMethod("Scan").setData("hello world").build();
            MessageProtos.Response response = stub.scan(new MessageController(), request);

            String res = String.format("\n>>>>>\n%s\n<<<<<", response);
            log.info(res);

            channel.closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }

    @AllArgsConstructor
    @Slf4j
    private static class BlockingChannelImpl implements BlockingRpcChannel {
        private Channel channel;

        /**
         * netty 的发送和获取数据流程都必须写在这里
         *
         * @param methodDescriptor
         * @param rpcController
         * @param request
         * @param response
         * @return
         * @throws ServiceException
         */
        @Override
        public Message callBlockingMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message request, Message response) throws ServiceException {
            MessageHandler handler = new MessageHandler();
            // netty 的数据发送逻辑
            response = handler.sendSyncMsg(channel, request);
            // netty 的数据获取逻辑
            handler.ackSyncMsg(response);
            return response;
        }
    }

}
