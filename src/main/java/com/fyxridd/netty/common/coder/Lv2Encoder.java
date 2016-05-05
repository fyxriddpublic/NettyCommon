package com.fyxridd.netty.common.coder;

import com.fyxridd.netty.common.MessageContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

/**
 * Lv2: 解析MessageContent
 */
public class Lv2Encoder extends MessageToByteEncoder<MessageContext> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageContext msg, ByteBuf out) throws Exception {
        //ver
        out.writeInt(msg.getVer().getNum());

        //namespace
        {
            byte[] bytes = msg.getNamespace().getBytes(CharsetUtil.UTF_8);
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
        }
        //name
        {
            byte[] bytes = msg.getName().getBytes(CharsetUtil.UTF_8);
            out.writeInt(bytes.length);
            out.writeBytes(bytes);
        }
        //内容
        ByteBuf buf = msg.getVer().getVerCoder().encode(msg.getContent());
        try {
            out.writeBytes(buf);
        } finally {
            buf.release();
        }
    }
}
