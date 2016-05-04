package com.fyxridd.netty.common.impl;

import com.fyxridd.netty.common.MessageManager;
import com.fyxridd.netty.common.message.v1.Ver1Message;
import com.fyxridd.netty.common.message.ver.Ver;
import com.fyxridd.netty.common.message.v1.Ver1MessageHandler;
import com.fyxridd.netty.common.message.Message;
import org.json.JSONObject;

import java.util.HashMap;

public class MessageManagerImpl implements MessageManager{
    private class MessageContext<T extends Message> {
        Ver1MessageHandler<T> ver1MessageHandler;

        MessageContext(Ver1MessageHandler<T> ver1MessageHandler) {
            this.ver1MessageHandler = ver1MessageHandler;
        }
    }

    private HashMap<Ver, HashMap<String, MessageContext>> messageContexts = new HashMap<>();

    public MessageManagerImpl() {
        for (Ver ver:Ver.values()) messageContexts.put(ver, new HashMap<String, MessageContext>());
    }

    @Override
    public <T extends Message> void registerVer1Json(Class<T> messageClass, Ver1MessageHandler<T> ver1MessageHandler) {
        messageContexts.get(Ver.Json).put(messageClass.getName(), new MessageContext(ver1MessageHandler));
    }

    @Override
    public JSONObject toJson(Ver1Message msg) {
        MessageContext messageContext = messageContexts.get(msg.getClass().getName());
        JSONObject json = new JSONObject();
        json.put("id", msg.getClass().getName());
        json.put("data", messageContext.ver1MessageHandler.toJson(msg.getMsg()));
        if (!msg.getExtra().isEmpty()) json.put("extra", msg.getExtra());
        return json;
    }

    @Override
    public Message fromJson(JSONObject json) {
        MessageContext messageContext = messageContexts.get(Ver.Json).get(json.getString("id"));
        if (messageContext == null) return null;
        return messageContext.ver1MessageHandler.fromJson(json.getJSONObject("data"));
    }
}
