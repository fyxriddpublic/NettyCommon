package com.fyxridd.netty.common.message.v1;

import com.fyxridd.netty.common.MessageMain;
import com.fyxridd.netty.common.message.MessageContent;
import com.fyxridd.netty.common.message.ver.VerCoder;
import com.fyxridd.netty.common.message.ver.VerMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Ver1Coder implements VerCoder{
    @Override
    public void encode(ByteBuf buf, MessageContent content) {
        buf.writeBytes(content.getContent().toString().getBytes(CharsetUtil.UTF_8));
    }

    @Override
    public VerMessage decode(ByteBuf buf, int read) {
        JSONObject json;
        ByteBuf b = Unpooled.buffer(read);
        try {
            buf.writeBytes(b);
            json = new JSONObject(b.toString(CharsetUtil.UTF_8));
        } finally {
            b.release();
        }

        //解析
        Map<String, Object> extra;
        if (json.has("extra")) extra = (Map<String, Object>) json.get("extra");
        else extra = new HashMap<>();

        return new Ver1Message(MessageMain.getMessageManager().fromJson(json.getJSONObject("data")), extra);
    }
}
