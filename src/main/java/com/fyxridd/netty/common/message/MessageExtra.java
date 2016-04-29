package com.fyxridd.netty.common.message;

import java.util.Map;

public class MessageExtra{
    private Message msg;
    //附加属性
    private Map<String, Object> extra;

    public MessageExtra(Message msg, Map<String, Object> extra) {
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
