package com.apktool.rpc.demo;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;

public class MessageController implements RpcController {

    private String errorMessage;

    @Override
    public void reset() {
        errorMessage = null;
    }

    @Override
    public boolean failed() {
        return errorMessage != null;
    }

    @Override
    public String errorText() {
        return errorMessage;
    }

    @Override
    public void startCancel() {

    }

    @Override
    public void setFailed(String s) {
        errorMessage = s;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void notifyOnCancel(RpcCallback<Object> rpcCallback) {

    }
}