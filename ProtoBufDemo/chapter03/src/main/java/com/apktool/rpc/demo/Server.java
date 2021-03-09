package com.apktool.rpc.demo;

import com.apktool.protobuf.MessageProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Server {
    public static void main(String[] args) {
        MessageProtos.Request request = MessageProtos.Request.newBuilder().setMethod("Scan").setData("hello").build();

        MessageProtos.AdminService service = new AdminServiceImpl();
        Descriptors.MethodDescriptor method = service.getDescriptorForType().findMethodByName(request.getMethod());

        service.callMethod(method, new MessageController(), request, new RpcCallback<Message>() {
            @Override
            public void run(Message response) {
                String rse = String.format("response=%s", response);
                log.info(rse);
            }
        });
    }

    private static class AdminServiceImpl extends MessageProtos.AdminService {
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
