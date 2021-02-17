package com.apktool.rpc.netty;

import com.apktool.protobuf.MessageProtos;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Handler implementation for the echo client.  It initiates the ping-pong
 * traffic between the echo client and server by sending the first message to
 * the server.
 */

@Slf4j
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    /**
     * client ill send a message when client start
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        MessageProtos.Request request = MessageProtos.Request.newBuilder()
                .setServiceName("echo")
                .setMethodName("sendRequest")
                .build();

        ctx.writeAndFlush(Unpooled.buffer(request.getSerializedSize()).writeBytes(request.toByteArray()));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] bytes = new byte[msg.readableBytes()];
        msg = msg.readBytes(bytes);
        MessageProtos.Response response = MessageProtos.Response.parseFrom(bytes);

        System.out.println(">>> "+ response);

        ctx.writeAndFlush(Unpooled.buffer(0));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn(cause.getMessage(), cause);
        ctx.close();
    }
}