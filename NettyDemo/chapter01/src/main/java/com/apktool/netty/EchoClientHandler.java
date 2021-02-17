package com.apktool.netty;

import java.nio.charset.StandardCharsets;

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
     * client will send a message when client start
     *
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String content = "Hello world";
        ByteBuf message = Unpooled.buffer(content.length());
        message.writeBytes(content.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(message);
    }

    /**
     * read message from context
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);

        System.out.println(new String(bytes));

        ctx.writeAndFlush(Unpooled.buffer(0));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn(cause.getMessage(), cause);
        ctx.close();
    }
}