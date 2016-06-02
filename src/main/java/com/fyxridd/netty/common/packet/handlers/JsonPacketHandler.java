package com.fyxridd.netty.common.packet.handlers;

import com.fyxridd.netty.common.packet.Packet;
import com.fyxridd.netty.common.packet.PacketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.json.JSONObject;

/**
 * Json包处理器
 */
public abstract class JsonPacketHandler<T extends Packet> implements PacketHandler<T>{
    @Override
    public T fromByteBuf(ByteBuf buf) {
        int length = buf.readInt();
        byte[] bytes = new byte[length];
        buf.readBytes(bytes, 0, length);
        String jsonStr = new String(bytes, CharsetUtil.UTF_8);
        return fromJson(new JSONObject(jsonStr));
    }

    @Override
    public ByteBuf toByteBuf(T t) {
        ByteBuf buf = Unpooled.buffer();
        byte[] bytes = toJson(t).toString().getBytes(CharsetUtil.UTF_8);
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        return buf;
    }

    public abstract T fromJson(JSONObject json);

    public abstract JSONObject toJson(T t);
}
