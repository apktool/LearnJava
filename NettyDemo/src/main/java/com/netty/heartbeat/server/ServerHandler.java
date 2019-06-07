package com.netty.heartbeat.server;

import com.netty.heartbeat.common.CustomHeartbeatHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author li.wengang
 * @date 2018-11-18 19:51
 */

public class ServerHandler extends CustomHeartbeatHandler {
    public ServerHandler() {
        super("server");
    }

    @Override
    protected void handleData(ChannelHandlerContext channelHandlerContext, ByteBuf buf) {
        byte[] data = new byte[buf.readableBytes() - 5];
        ByteBuf responseBuf = Unpooled.copiedBuffer(buf);
        buf.skipBytes(5);
        buf.readBytes(data);
        String content = new String(data);
        System.out.println(name + " get content: " + content);
        channelHandlerContext.write(responseBuf);
    }

    @Override
    protected void handleReaderIdle(ChannelHandlerContext ctx) {
        super.handleReaderIdle(ctx);
        System.err.println("---client " + ctx.channel().remoteAddress().toString() + " reader timeout, close it---");
        ctx.close();
    }
}
