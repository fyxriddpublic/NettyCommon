package com.fyxridd.netty.common.coder;

import com.fyxridd.netty.common.packet.PacketContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketContextToByteBufEncoder extends MessageToByteEncoder<PacketContext>{
    @Override
    protected void encode(ChannelHandlerContext ctx, PacketContext msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getPacketType().getPacketId());
        out.writeInt(msg.getPacketVer());
        out.writeBytes(msg.getPacketType().getPacketHandler(msg.getPacketVer()).toByteBuf(msg.getPacket()));
    }
}
