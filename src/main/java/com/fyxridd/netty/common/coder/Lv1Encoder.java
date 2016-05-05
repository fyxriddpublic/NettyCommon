package com.fyxridd.netty.common.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Lv1: 解析完整的ByteBuf
 */
public class Lv1Encoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        try {
            out.writeInt(msg.readableBytes());
            out.writeBytes(msg);
        } finally {
            msg.release();
        }
    }
}
