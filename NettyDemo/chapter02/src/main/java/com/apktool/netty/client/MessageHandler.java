package com.apktool.netty.client;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * dataId 是请求报文中的字段，每发送一次，该Id需要原子自增一次
 */
@Slf4j
public class MessageHandler {
    public String sendSyncMsg(Channel channel, String message) {

        ByteBuf byteBuf = Unpooled.copiedBuffer(message, CharsetUtil.UTF_8);
        String result = "";

        try {
            ChannelFuture future = channel.writeAndFlush(byteBuf);

            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        log.info("Send successfully");
                    } else {
                        log.info("Send failed");
                    }
                }
            });

            // 等待 8 秒
            SyncFuture<String> syncFuture = new SyncFuture();
            String dataId = "1";
            CacheUtil.cacheFuture.put(dataId, syncFuture);
            result = syncFuture.get(5, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }

    public void ackSyncMsg(String msg) {
        log.info("ACK message: {}", msg);

        String dataId = "1";
        // 从缓存中获取数据
        SyncFuture<String> syncFuture = CacheUtil.cacheFuture.get(dataId);

        if (syncFuture != null) {
            syncFuture.setResponse(msg);
            //主动释放
            CacheUtil.cacheFuture.remove(dataId);
        }
    }
}
