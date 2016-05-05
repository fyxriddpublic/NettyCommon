package com.fyxridd.netty.common.message;

import org.json.JSONObject;

/*
 * 信息
 * (子类使用多实例模式)
 */
public interface Message {
    /**
     * 转化为Json
     */
    JSONObject toJson();
}
