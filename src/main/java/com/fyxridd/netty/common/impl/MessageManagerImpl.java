package com.fyxridd.netty.common.impl;

import com.fyxridd.netty.common.Listener;
import com.fyxridd.netty.common.MessageManager;
import com.fyxridd.netty.common.MessageContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManagerImpl implements MessageManager{
    private HashMap<String, HashMap<String, List<Listener>>> handlers = new HashMap<>();

    @Override
    public boolean register(String namespace) {
        if (handlers.containsKey(namespace)) return false;
        handlers.put(namespace, new HashMap<String, List<Listener>>());
        return true;
    }

    @Override
    public void addAllListener(String namespace, Listener listener) {
        HashMap<String, List<Listener>> hash = handlers.get(namespace);
        if (hash != null) {
            for (Map.Entry<String, List<Listener>> entry:hash.entrySet()) {
                entry.getValue().add(listener);
            }
        }
    }

    @Override
    public void addListener(String namespace, Listener listener, String... names) {
        if (names != null && names.length > 0) {
            HashMap<String, List<Listener>> hash = handlers.get(namespace);
            if (hash != null) {
                for (String name:names) {
                    List<Listener> list = hash.get(name);
                    if (list != null) list.add(listener);
                }
            }
        }
    }

    @Override
    public void trigger(MessageContext message) {
        HashMap<String, List<Listener>> hash = handlers.get(message.getNamespace());
        if (hash != null) {
            List<Listener> list = hash.get(message.getName());
            if (list != null) {
                for (Listener listener: list) listener.onEvent(message);
            }
        }
    }
}
