package com.fyxridd.netty.common.message.v1;

import com.fyxridd.netty.common.message.Message;
import org.json.JSONObject;

/**
 * 信息处理器
 */
public interface Ver1MessageHandler<T extends Message> {
    /**
     * @return 可为null
     */
    T fromJson(JSONObject json);

    JSONObject toJson(Message msg);
}
