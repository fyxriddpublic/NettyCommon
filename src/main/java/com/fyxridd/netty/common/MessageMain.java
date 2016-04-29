package com.fyxridd.netty.common;

import com.fyxridd.netty.common.impl.MessageManagerImpl;

public class MessageMain {
    private static MessageManager messageManager;

    public static MessageManager getMessageManager() {
        if (messageManager == null) messageManager = new MessageManagerImpl();
        return messageManager;
    }
}
