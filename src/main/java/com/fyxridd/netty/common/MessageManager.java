package com.fyxridd.netty.common;

public interface MessageManager {
    /**
     * 注册命名空间
     * @return 是否注册成功,如果已经有此命名空间,则返回false
     */
    boolean register(String namespace);

    /**
     * 添加监听命名空间内的全部消息
     * @param namespace 命名空间
     * @param listener 监听器
     */
    void addAllListener(String namespace, Lv2MessageListener listener);

    /**
     * 添加监听命名空间内的指定消息
     * @param namespace 命名空间
     * @param listener 监听器
     * @param names 指定消息列表,为空则不监听
     */
    void addListener(String namespace, Lv2MessageListener listener, String... names);

    /**
     * 触发事件,调用监听
     * @param message 消息内容
     */
    void trigger(MessageContent message);
}
