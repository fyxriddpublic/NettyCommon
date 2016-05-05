package com.fyxridd.netty.common;

public interface MessageManager {
    /**
     * 注册命名空间
     * @return 是否注册成功,如果已经有此命名空间,则返回false
     */
    boolean registerNamespace(String namespace);

    /**
     * 监听
     * 一个类对应一个监听
     * @param namespace 命名空间
     * @param c 消息类
     * @param listener 监听器
     * @return 是否注册成功,如果已经有此类的监听或命名空间不存在,则注册失败
     */
    boolean listen(String namespace, Class<? extends Message> c, Listener listener);

    /**
     * 触发
     * @param message 消息内容
     */
    void trigger(MessageContext message);
}
