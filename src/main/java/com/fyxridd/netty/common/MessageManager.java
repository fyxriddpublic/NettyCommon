package com.fyxridd.netty.common;

import com.fyxridd.netty.common.message.v1.Ver1Message;
import com.fyxridd.netty.common.message.v1.Ver1MessageHandler;
import com.fyxridd.netty.common.message.Message;
import org.json.JSONObject;

/**
 * 消息管理
 * --> 消息读入 --> MessageManager.fromJson  .. MessageExtra ..  --> MessageManager.toJson --> 消息写出
 */
public interface MessageManager {
    <T extends Message> void registerVer1Json(Class<T> messageClass, Ver1MessageHandler<T> ver1MessageHandler);

    JSONObject toJson(Ver1Message msg);

    /**
     * @return 可为null
     */
    Message fromJson(JSONObject json);
}
