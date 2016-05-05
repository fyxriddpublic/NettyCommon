package com.fyxridd.netty.common.message.ver.v1;

import com.fyxridd.netty.common.message.ver.VerMessage;
import org.json.JSONObject;

/**
 * 信息处理器
 */
public interface Ver1MessageHandler<T extends VerMessage> {
    /**
     * @return 可为null
     */
    T fromJson(JSONObject json);

    JSONObject toJson(VerMessage msg);
}
