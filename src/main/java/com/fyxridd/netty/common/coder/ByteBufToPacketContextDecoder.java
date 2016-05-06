package com.fyxridd.netty.common.coder;

import com.fyxridd.netty.common.packet.PacketContext;
import com.fyxridd.netty.common.packet.PacketType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ByteBufToPacketContextDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int packetId = in.readInt();
        int packetVer = in.readInt();
        PacketType packetType = PacketType.getById(packetId);
        out.add(new PacketContext(packetType, packetVer, packetType.getPacketHandler(packetVer).fromByteBuf(in)));
    }
}
