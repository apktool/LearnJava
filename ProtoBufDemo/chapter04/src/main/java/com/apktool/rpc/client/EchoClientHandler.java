package com.apktool.rpc.client;

import com.apktool.protobuf.MessageProtos;
import com.google.protobuf.InvalidProtocolBufferException;

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
     * read message from context
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws InvalidProtocolBufferException {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);

        MessageProtos.Response response = MessageProtos.Response.parseFrom(bytes);
        new MessageHandler().ackSyncMsg(response);

        ctx.writeAndFlush(Unpooled.buffer(0));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn(cause.getMessage(), cause);
        ctx.close();
    }
}