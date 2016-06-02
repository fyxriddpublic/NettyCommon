package com.fyxridd.netty.common.coder;

import com.fyxridd.netty.common.packet.PacketContext;
import com.fyxridd.netty.common.packet.PacketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

public class PacketContextToByteBufEncoder extends MessageToByteEncoder<PacketContext>{
    @Override
    protected void encode(ChannelHandlerContext ctx, PacketContext msg, ByteBuf out) throws Exception {
        PacketHandler packetHandler = msg.getPacketType().getPacketHandler(msg.getPacketVer());
        if (packetHandler != null) {
            byte[] bytes = msg.getPacketType().getPacketGroup().getBytes(CharsetUtil.UTF_8);
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
            out.writeInt(msg.getPacketType().getPacketId());
            out.writeInt(msg.getPacketVer());
            {
                byte[] bytes1 = msg.getUid().toString().getBytes(CharsetUtil.UTF_8);
                out.writeInt(bytes1.length);
                out.writeBytes(bytes1);
            }
            out.writeLong(msg.getTime());
            out.writeBytes(packetHandler.toByteBuf(msg.getPacket()));
        }

    }
}
