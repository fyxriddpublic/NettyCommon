package com.fyxridd.netty.common;

import com.fyxridd.netty.common.message.ver.VerMessage;
import com.fyxridd.netty.common.message.ver.v1.Ver1MessageHandler;

public interface MessageManager {
    /**
     * 注册命名空间
     * @return 是否注册成功,如果已经有此命名空间,则返回false
     */
    boolean register(String namespace);

    /**
     * 注册消息定义
     * @param namespace 命名空间
     * @param name 消息名
     * @param ver1MessageHandler
     * @param <T>
     */
    <T extends VerMessage> void registerVer1Json(String namespace, String name, Ver1MessageHandler<T> ver1MessageHandler);
}
