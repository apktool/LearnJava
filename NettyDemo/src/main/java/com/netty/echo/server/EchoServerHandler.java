package com.netty.echo.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author apktool
 * @date 2018-09-21 10:37
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf in = (ByteBuf) msg;
        ByteBuf inCopy = Unpooled.copiedBuffer(in);

        try {
            while (in.isReadable()) {
                System.out.print((char) in.readByte());
            }
        } finally {
            in.release();
        }

        ctx.writeAndFlush(inCopy);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
