package com.apktool.rpc.client;

import java.io.IOException;

import com.apktool.protobuf.MessageProtos;
import com.google.protobuf.Message;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * dataId 是请求报文中的字段，每发送一次，该Id需要原子自增一次
 */
@Slf4j
public class MessageHandler {
    public MessageProtos.Response sendSyncMsg(Channel channel, Message message) {
        try {
            ChannelFuture future = channel.writeAndFlush(Unpooled.copiedBuffer(message.toByteArray()));
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

            String dataId = "1";
            BlockingRpcCallback<MessageProtos.Response> callback = new BlockingRpcCallback();
            CacheUtil.cacheFuture.put(dataId, callback);

            return callback.get();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public void ackSyncMsg(Message message) {
        log.info("ACK message: {}", message);

        String dataId = "1";
        BlockingRpcCallback<Message> done = CacheUtil.cacheFuture.get(dataId);

        if (done != null) {
            done.run(message);
            //主动释放
            CacheUtil.cacheFuture.remove(dataId);
        }
    }
}
