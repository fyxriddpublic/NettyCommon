package com.fyxridd.netty.common.message.coder;

import com.fyxridd.netty.common.message.MessageContent;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 格式:
 * 长度+MessageContent(ver+内容)
 */
public class MessageEncoder extends MessageToByteEncoder<MessageContent>{
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageContent content, ByteBuf buf) throws Exception {
        //生成内容
        ByteBuf tmp = ctx.alloc().buffer();
        tmp.writeInt(content.getVer().getNum());
        content.getVer().getVerCoder().encode(tmp, content);

        //写入Head
        buf.writeInt(tmp.readableBytes());
        //写入Body
        buf.writeBytes(tmp);
    }
}
