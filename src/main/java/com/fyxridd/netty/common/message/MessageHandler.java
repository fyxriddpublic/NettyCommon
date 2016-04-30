package com.fyxridd.netty.common.message;

import org.json.JSONObject;

/**
 * 信息处理器
 */
public interface MessageHandler<T extends Message> {
    /**
     * @return 可为null
     */
    T fromJson(JSONObject json);

    JSONObject toJson(Message msg);
}
