package com.fyxridd.netty.common.ver.v1;

import com.fyxridd.netty.common.ver.VerCoder;
import com.fyxridd.netty.common.ver.VerMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Ver1Coder implements VerCoder{
    @Override
    public ByteBuf encode(VerMessage content) {
        Ver1Message ver1Message = (Ver1Message) content;
        JSONObject json = new JSONObject();
        if (ver1Message.getExtra() != null && !ver1Message.getExtra().isEmpty()) json.put("extra", ver1Message.getExtra());
        json.put("data", ver1Message.getMsg());

        byte[] bytes = json.toString().getBytes(CharsetUtil.UTF_8);
        ByteBuf buf = Unpooled.buffer(bytes.length);
        buf.writeBytes(bytes);
        return buf;
    }

    @Override
    public VerMessage decode(ByteBuf buf) {
        JSONObject json = new JSONObject(buf.toString(CharsetUtil.UTF_8));

        //解析
        Map<String, Object> extra;
        if (json.has("extra")) extra = (Map<String, Object>) json.get("extra");
        else extra = new HashMap<>();

        return new Ver1Message(json.getJSONObject("data"), extra);
    }
}
