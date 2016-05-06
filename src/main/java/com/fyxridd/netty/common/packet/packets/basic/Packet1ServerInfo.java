package com.fyxridd.netty.common.packet.packets.basic;

import com.fyxridd.netty.common.packet.Packet;
import com.fyxridd.netty.common.packet.PacketHandler;
import com.fyxridd.netty.common.packet.PacketType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import org.json.JSONObject;

/**
 * 服务器信息
 */
public class Packet1ServerInfo implements Packet{
    public static final PacketType PACKET_TYPE= PacketType.ServerInfo;

    static {
        /**
         * 版本1
         */
        PACKET_TYPE.registerPackerHandler(1, new PacketHandler<Packet1ServerInfo>() {
            @Override
            public Packet1ServerInfo fromByteBuf(ByteBuf buf) {
                int length = buf.readInt();
                byte[] bytes = new byte[length];
                String jsonStr = new String(bytes, CharsetUtil.UTF_8);
                JSONObject json = new JSONObject(jsonStr);
                String name = json.has("name")?json.getString("name"):null;
                String url = json.has("url")?json.getString("url"):null;
                String ver = json.has("ver")?json.getString("ver"):null;
                return new Packet1ServerInfo(name, url, ver);
            }

            @Override
            public ByteBuf toByteBuf(Packet1ServerInfo packet) {
                JSONObject json = new JSONObject();
                if (packet.getName() != null) json.put("name", packet.getName());
                if (packet.getUrl() != null) json.put("url", packet.getUrl());
                if (packet.getVer() != null) json.put("ver", packet.getVer());

                ByteBuf buf = Unpooled.buffer();
                byte[] bytes = json.toString().getBytes(CharsetUtil.UTF_8);
                buf.writeBytes(bytes);
                return buf;
            }
        });
    }

    private String name;
    private String url;
    private String ver;

    public Packet1ServerInfo(String name, String url, String ver) {
        this.name = name;
        this.url = url;
        this.ver = ver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
}
