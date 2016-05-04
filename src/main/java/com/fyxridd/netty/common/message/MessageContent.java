package com.fyxridd.netty.common.message;

import com.fyxridd.netty.common.message.ver.Ver;

/**
 * 消息体
 */
public class MessageContent {
    private Ver ver;
    private Object content;

    public MessageContent(Ver ver, Object content) {
        this.ver = ver;
        this.content = content;
    }

    public Ver getVer() {
        return ver;
    }

    public Object getContent() {
        return content;
    }
}
