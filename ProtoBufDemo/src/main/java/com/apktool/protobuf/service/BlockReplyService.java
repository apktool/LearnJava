package com.apktool.protobuf.service;

import com.apktool.protobuf.rpc.Message;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author apktool
 * @package com.apktool.protobuf.service
 * @class BlockReplyService
 * @date 2020-09-25 00:01:38
 */

@Slf4j
public class BlockReplyService implements Message.ReplyService.BlockingInterface {
    @Override
    public Message.Msg call(RpcController controller, Message.Msg request) throws ServiceException {
        log.debug("接收反馈消息:" + request.getContent());
        if (controller.isCanceled()) {
            return null;
        }
        return Message.Msg.newBuilder().setContent("收到反馈成功.").build();
    }
}

