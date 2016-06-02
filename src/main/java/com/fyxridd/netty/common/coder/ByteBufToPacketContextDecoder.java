package com.fyxridd.netty.common.coder;

import com.fyxridd.netty.common.packet.PacketContext;
import com.fyxridd.netty.common.packet.PacketHandler;
import com.fyxridd.netty.common.packet.PacketType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;
import java.util.UUID;

public class ByteBufToPacketContextDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        byte[] packetGroupBytes = new byte[in.readInt()];
        in.readBytes(packetGroupBytes);
        String packetGroup = new String(packetGroupBytes, CharsetUtil.UTF_8);
        int packetId = in.readInt();
        int packetVer = in.readInt();
        UUID uid;
        {
            byte[] bytes = new byte[in.readInt()];
            in.readBytes(bytes);
            uid = UUID.fromString(new String(bytes, CharsetUtil.UTF_8));
        }
        long time = in.readLong();
        PacketType packetType = PacketType.get(packetGroup, packetId);
        PacketHandler packetHandler = packetType.getPacketHandler(packetVer);
        if (packetHandler != null) out.add(new PacketContext(packetType, packetVer, uid, time, packetHandler.fromByteBuf(in)));
    }
}
