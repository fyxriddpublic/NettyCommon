package com.fyxridd.netty.common;

import com.fyxridd.netty.common.message.MessageExtra;
import com.fyxridd.netty.common.message.MessageHandler;
import com.fyxridd.netty.common.message.Message;
import org.json.JSONObject;

/**
 * 消息管理
 * --> 消息读入 --> MessageManager.fromJson  .. MessageExtra ..  --> MessageManager.toJson --> 消息写出
 */
public interface MessageManager {
    <T extends Message> void register(Class<T> messageClass, MessageHandler<T> messageHandler);

    JSONObject toJson(MessageExtra msg);

    /**
     * @return 可为null
     */
    MessageExtra fromJson(JSONObject json);
}
