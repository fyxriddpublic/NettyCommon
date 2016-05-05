package com.fyxridd.netty.common.impl;

import com.fyxridd.netty.common.Lv2MessageListener;
import com.fyxridd.netty.common.MessageManager;
import com.fyxridd.netty.common.MessageContent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageManagerImpl implements MessageManager{
    private HashMap<String, HashMap<String, List<Lv2MessageListener>>> handlers = new HashMap<>();

    @Override
    public boolean register(String namespace) {
        if (handlers.containsKey(namespace)) return false;
        handlers.put(namespace, new HashMap<String, List<Lv2MessageListener>>());
        return true;
    }

    @Override
    public void addAllListener(String namespace, Lv2MessageListener listener) {
        HashMap<String, List<Lv2MessageListener>> hash = handlers.get(namespace);
        if (hash != null) {
            for (Map.Entry<String, List<Lv2MessageListener>> entry:hash.entrySet()) {
                entry.getValue().add(listener);
            }
        }
    }

    @Override
    public void addListener(String namespace, Lv2MessageListener listener, String... names) {
        if (names != null && names.length > 0) {
            HashMap<String, List<Lv2MessageListener>> hash = handlers.get(namespace);
            if (hash != null) {
                for (String name:names) {
                    List<Lv2MessageListener> list = hash.get(name);
                    if (list != null) list.add(listener);
                }
            }
        }
    }

    @Override
    public void trigger(MessageContent message) {
        HashMap<String, List<Lv2MessageListener>> hash = handlers.get(message.getNamespace());
        if (hash != null) {
            List<Lv2MessageListener> list = hash.get(message.getName());
            if (list != null) {
                for (Lv2MessageListener listener: list) listener.onEvent(message);
            }
        }
    }
}
