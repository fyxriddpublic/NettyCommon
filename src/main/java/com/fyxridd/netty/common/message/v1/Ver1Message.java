package com.fyxridd.netty.common.message.v1;

import com.fyxridd.netty.common.message.Message;
import com.fyxridd.netty.common.message.ver.VerMessage;

import java.util.Map;

public class Ver1Message implements VerMessage {
    private Message msg;
    //附加属性
    private Map<String, Object> extra;

    public Ver1Message(Message msg, Map<String, Object> extra) {
        this.msg = msg;
        this.extra = extra;
    }

    public Message getMsg() {
        return msg;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }
}
