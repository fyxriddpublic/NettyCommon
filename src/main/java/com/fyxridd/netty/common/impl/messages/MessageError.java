package com.fyxridd.netty.common.impl.messages;

import com.fyxridd.netty.common.MessageMain;
import com.fyxridd.netty.common.message.v1.Ver1MessageHandler;
import com.fyxridd.netty.common.message.Message;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 错误
 */
public class MessageError implements Message{
    //注册错误信息
    static {
        MessageMain.getMessageManager().register(MessageError.class, new Ver1MessageHandler<MessageError>() {
            @Override
            public MessageError fromJson(JSONObject json) {
                try {
                    String code = json.getString("code");
                    String msg = json.getString("msg");
                    return new MessageError(code, msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public JSONObject toJson(Message msg) {
                JSONObject json = new JSONObject();
                MessageError m = (MessageError) msg;
                json.put("code", m.getCode());
                json.put("msg", m.getMsg());
                return json;
            }
        });
    }

    private String code;
    private String msg;

    public MessageError(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
