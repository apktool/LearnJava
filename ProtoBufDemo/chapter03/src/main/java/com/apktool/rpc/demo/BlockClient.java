package com.apktool.rpc.demo;

import com.apktool.protobuf.MessageProtos;
import com.google.protobuf.BlockingRpcChannel;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Message;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BlockClient {
    public static void main(String[] args) throws ServiceException {
        MessageProtos.AdminService.BlockingInterface stub = MessageProtos.AdminService.newBlockingStub(new BlockingChannelImpl());

        MessageProtos.Request request = MessageProtos.Request.newBuilder().setMethod("Scan").setData("hello").build();
        MessageProtos.Response response = stub.scan(new MessageController(), request);

        String rse = String.format("response=%s", response);
        log.info(rse);
    }

    public static class BlockingChannelImpl implements BlockingRpcChannel {
        /**
         * @param methodDescriptor
         * @param rpcController
         * @param request
         * @param response
         * @return
         * @throws ServiceException
         */
        @Override
        public Message callBlockingMethod(Descriptors.MethodDescriptor methodDescriptor, RpcController rpcController, Message request, Message response) throws ServiceException {
            String col = String.format("controller=%s", rpcController.errorText());
            String rst = String.format("request=%s", request);
            String rse = String.format("response=%s", response);
            log.info(col);
            log.info(rst);
            log.info(rse);
            return response;
        }
    }
}