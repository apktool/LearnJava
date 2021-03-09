package com.apktool.rpc.demo;

import com.apktool.protobuf.MessageProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoBlockClient {
    public static void main(String[] args) {
        MessageProtos.AdminService.Stub stub = MessageProtos.AdminService.newStub(new ChannelImpl());

        MessageProtos.Request request = MessageProtos.Request.newBuilder().setMethod("Scan").setData("hello").build();
        stub.scan(new MessageController(), request, new RpcCallback<MessageProtos.Response>() {
            @Override
            public void run(MessageProtos.Response response) {
                String rse = String.format("response=%s", response);
                log.info(rse);
            }
        });
    }

    public static class ChannelImpl implements RpcChannel {
        @Override
        public void callMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message request, Message response, RpcCallback<Message> done) {
            String col = String.format("controller=%s", rpcController.errorText());
            String rst = String.format("request=%s", request);
            String rse = String.format("response=%s", response);
            log.info(col);
            log.info(rst);
            log.info(rse);
            done.run(response);
        }
    }
}