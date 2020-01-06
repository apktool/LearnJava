package com.livy.rpc;

/**
 * @author apktool
 * @package com.livy.rpc
 * @class Message
 * @description TODO
 * @date 2020-01-06 23:28
 */
public class Message {
    private final String message;

    public Message(String message) {
        this.message = message;
    }

    public Message() {
        this(null);
    }

    public String getMessage() {
        return message;
    }
}
