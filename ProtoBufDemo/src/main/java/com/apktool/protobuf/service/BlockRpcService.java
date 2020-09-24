package com.apktool.protobuf.service;

import com.apktool.protobuf.rpc.Message;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import com.googlecode.protobuf.pro.duplex.ClientRpcController;
import com.googlecode.protobuf.pro.duplex.RpcClientChannel;
import com.googlecode.protobuf.pro.duplex.execute.ServerRpcController;

import lombok.extern.slf4j.Slf4j;

/**
 * @author apktool
 * @package com.apktool.protobuf.service
 * @class BlockRpcService
 * @date 2020-09-24 23:48:29
 */

@Slf4j
public class BlockRpcService implements Message.RpcService.BlockingInterface {
    @Override
    public Message.Response call(RpcController controller, Message.Request request) throws ServiceException {
        if (controller.isCanceled()) {
            return null;
        }
        log.info("接收到数据：");
        log.info("serviceName : " + request.getServiceName());
        log.info("methodName : " + request.getMethodName());
        log.info("params : " + request.getParams());

        RpcClientChannel channel = ServerRpcController.getRpcChannel(controller);
        Message.ReplyService.BlockingInterface clientService = Message.ReplyService.newBlockingStub(channel);
        ClientRpcController clientController = channel.newRpcController();
        clientController.setTimeoutMs(3000);
        //调用过程反馈消息
        Message.Msg msg = Message.Msg.newBuilder().setContent("success.").build();
        clientService.call(clientController, msg);
        Message.Response response = Message.Response.newBuilder().setCode(0).setMsg("处理完成").setData("server hello").build();
        return response;
    }
}