package com.apktool.rpc.server;

import com.apktool.protobuf.MessageProtos;
import com.apktool.rpc.common.MessageController;
import com.google.protobuf.Descriptors;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

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
        msg.readBytes(bytes);
        MessageProtos.Request request = MessageProtos.Request.parseFrom(bytes);

        MessageProtos.AdminService service = new AdminServiceImpl();
        Descriptors.MethodDescriptor method = service.getDescriptorForType().findMethodByName(request.getMethod());

        service.callMethod(method, new MessageController(), request, new RpcCallback<Message>() {
            @Override
            public void run(Message response) {
                String rse = String.format("response=%s", response);
                log.info(rse);
                ctx.writeAndFlush(Unpooled.copiedBuffer(response.toByteArray()));
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn(cause.getMessage(), cause);
        ctx.close();
    }

    private class AdminServiceImpl extends MessageProtos.AdminService {
        @Override
        public void put(RpcController controller, MessageProtos.Request request, RpcCallback<MessageProtos.Response> done) {
            String data = String.format("put: %s too.", request.getData());
            MessageProtos.Response response = MessageProtos.Response.newBuilder().setCode(200).setData(data).setMsg("").build();
            done.run(response);
        }

        @Override
        public void get(RpcController controller, MessageProtos.Request request, RpcCallback<MessageProtos.Response> done) {
            String data = String.format("get: %s too.", request.getData());
            MessageProtos.Response response = MessageProtos.Response.newBuilder().setCode(200).setData(data).setMsg("").build();
            done.run(response);
        }

        @Override
        public void scan(RpcController controller, MessageProtos.Request request, RpcCallback<MessageProtos.Response> done) {
            String data = String.format("scan: %s too.", request.getData());
            MessageProtos.Response response = MessageProtos.Response.newBuilder().setCode(200).setData(data).setMsg("").build();
            done.run(response);
        }
    }
}
