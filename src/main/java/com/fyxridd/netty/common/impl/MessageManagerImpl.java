package com.fyxridd.netty.common.impl;

import com.fyxridd.netty.common.MessageManager;
import com.fyxridd.netty.common.message.MessageExtra;
import com.fyxridd.netty.common.message.MessageHandler;
import com.fyxridd.netty.common.message.Message;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MessageManagerImpl implements MessageManager{
    private class MessageContext<T extends Message> {
        MessageHandler<T> messageHandler;

        MessageContext(MessageHandler<T> messageHandler) {
            this.messageHandler = messageHandler;
        }
    }

    private HashMap<String, MessageContext> messageContexts = new HashMap<>();

    @Override
    public <T extends Message> void register(Class<T> messageClass, MessageHandler<T> messageHandler) {
        messageContexts.put(messageClass.getName(), new MessageContext(messageHandler));
    }

    @Override
    public JSONObject toJson(MessageExtra msg) {
        MessageContext messageContext = messageContexts.get(msg.getClass().getName());
        JSONObject json = new JSONObject();
        json.put("id", msg.getClass().getName());
        json.put("data", messageContext.messageHandler.toJson(msg.getMsg()));
        if (!msg.getExtra().isEmpty()) json.put("extra", msg.getExtra());
        return json;
    }

    @Override
    public MessageExtra fromJson(JSONObject json) {
        try {
            MessageContext messageContext = messageContexts.get(json.getString("id"));
            Message msg = messageContext.messageHandler.fromJson(json.getJSONObject("data"));
            Map<String, Object> extra;
            if (json.has("extra")) extra = (Map<String, Object>) json.get("extra");
            else extra = new HashMap<>();
            return new MessageExtra(msg, extra);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
