package com.fyxridd.netty.common.impl.messages;

import com.fyxridd.netty.common.MessageMain;
import com.fyxridd.netty.common.message.MessageHandler;
import com.fyxridd.netty.common.message.Message;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 消息
 */
public class MessageMsg implements Message{
    //注册消息信息
    static {
        MessageMain.getMessageManager().register(MessageMsg.class, new MessageHandler<MessageMsg>() {
            @Override
            public MessageMsg fromJson(JSONObject json) {
                try {
                    String msg = json.getString("msg");
                    return new MessageMsg(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public JSONObject toJson(Message msg) {
                JSONObject json = new JSONObject();
                MessageMsg m = (MessageMsg) msg;
                json.put("msg", m.getMsg());
                return json;
            }
        });
    }

    private String msg;

    public MessageMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
