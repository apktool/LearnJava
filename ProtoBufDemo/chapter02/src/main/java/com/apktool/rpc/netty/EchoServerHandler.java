package com.apktool.rpc.netty;

import com.apktool.protobuf.MessageProtos;
import com.google.protobuf.InvalidProtocolBufferException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Handler implementation for the echo server.
 */
@Slf4j
@Sharable
public class EchoServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws InvalidProtocolBufferException {
        byte[] bytes = new byte[msg.readableBytes()];
        msg = msg.readBytes(bytes);
        MessageProtos.Request request = MessageProtos.Request.parseFrom(bytes);

        System.out.println(">>> " + request);

        MessageProtos.Response response = MessageProtos.Response.newBuilder()
                .setCode(200)
                .setData("Success")
                .setMsg("")
                .build();

        ctx.writeAndFlush(Unpooled.buffer(response.getSerializedSize()).writeBytes(response.toByteArray()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn(cause.getMessage(), cause);
        ctx.close();
    }
}
