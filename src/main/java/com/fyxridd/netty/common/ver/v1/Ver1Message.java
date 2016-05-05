package com.fyxridd.netty.common.ver.v1;

import com.fyxridd.netty.common.MessageContent;
import org.json.JSONObject;

import java.util.Map;

public class Ver1Message implements MessageContent {
    private JSONObject msg;
    //附加属性
    private Map<String, Object> extra;

    public Ver1Message(JSONObject msg, Map<String, Object> extra) {
        this.msg = msg;
        this.extra = extra;
    }

    public JSONObject getMsg() {
        return msg;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }
}
