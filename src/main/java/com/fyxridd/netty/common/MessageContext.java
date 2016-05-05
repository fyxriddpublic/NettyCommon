package com.fyxridd.netty.common;

import com.fyxridd.netty.common.ver.Ver;

/**
 * 消息上下文
 */
public class MessageContext {
    private Ver ver;
    private String namespace;
    private String name;
    private MessageContent content;

    public MessageContext(Ver ver, String namespace, String name, MessageContent content) {
        this.ver = ver;
        this.namespace = namespace;
        this.name = name;
        this.content = content;
    }

    public Ver getVer() {
        return ver;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public MessageContent getContent() {
        return content;
    }
}
