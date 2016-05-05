package com.fyxridd.netty.common;

import com.fyxridd.netty.common.ver.VerMessage;

public class MessageContent {
    private String namespace;
    private String name;
    private VerMessage verMessage;

    public MessageContent(String namespace, String name, VerMessage verMessage) {
        this.namespace = namespace;
        this.name = name;
        this.verMessage = verMessage;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public VerMessage getVerMessage() {
        return verMessage;
    }
}
