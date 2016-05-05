package com.fyxridd.netty.common.impl;

import com.fyxridd.netty.common.Listener;
import com.fyxridd.netty.common.Message;
import com.fyxridd.netty.common.MessageManager;
import com.fyxridd.netty.common.MessageContext;

import java.util.HashMap;

public class MessageManagerImpl implements MessageManager{
    private HashMap<String, HashMap<String, Listener>> handlers = new HashMap<>();

    @Override
    public boolean registerNamespace(String namespace) {
        if (handlers.containsKey(namespace)) return false;
        handlers.put(namespace, new HashMap<String, Listener>());
        return true;
    }

    @Override
    public boolean listen(String namespace, Class<? extends Message> c, Listener listener) {
        HashMap<String, Listener> hash = handlers.get(namespace);
        if (hash != null) {
            Listener old = hash.get(c.getName());
            if (old != null) return false;
            hash.put(c.getName(), listener);
            return true;
        }
        return false;
    }

    @Override
    public void trigger(MessageContext message) {
        HashMap<String, Listener> hash = handlers.get(message.getNamespace());
        if (hash != null) {
            Listener listener = hash.get(message.getName());
            if (listener != null) listener.onEvent(message);
        }
    }
}
