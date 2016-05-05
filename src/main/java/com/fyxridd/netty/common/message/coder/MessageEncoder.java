package com.fyxridd.netty.common.message.coder;

import com.fyxridd.netty.common.message.MessageContent;
import com.fyxridd.netty.common.message.ver.VerMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<VerMessage>{
    @Override
    protected void encode(ChannelHandlerContext ctx, VerMessage content, ByteBuf buf) throws Exception {
        ByteBuf b = null, tmp = null;
        try {
            //生成内容
            b = content.getVer().getVerCoder().encode(content);
            tmp = ctx.alloc().buffer();
            tmp.writeInt(content.getVer().getNum());
            tmp.writeBytes(b);

            //长度
            buf.writeInt(tmp.readableBytes());
            //内容
            buf.writeBytes(tmp);
        } finally {
            if (b != null) b.release();
            if (tmp != null) tmp.release();
        }
    }
}
