package com.livy.rpc;

import com.livy.rpc.rsc.rpc.RpcDispatcher;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author apktool
 * @package com.livy.rpc
 * @class RpcDispatcherImpl
 * @description TODO
 * @date 2020-01-06 22:17
 */
public class RpcDispatcherImpl extends RpcDispatcher {
    protected Message handle(ChannelHandlerContext ctx, Message msg) {
        return msg;
    }
}
