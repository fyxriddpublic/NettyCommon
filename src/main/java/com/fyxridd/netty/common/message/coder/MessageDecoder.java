package com.fyxridd.netty.common.message.coder;

import com.fyxridd.netty.common.message.ver.Ver;
import com.fyxridd.netty.common.message.ver.VerMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public abstract class MessageDecoder extends ByteToMessageDecoder{
    private int read;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        //长度
        if (read == 0) {
            if (buf.readableBytes() < 2) return;//这里假设int长度为2字节
            else {
                read = buf.readInt();
                //异常
                if (read <= 0) {
                    buf.release();
                    ctx.close();
                    return;
                }
            }
        }

        //内容
        if (buf.readableBytes() >= read) {
            Ver ver = Ver.getVerByNum(buf.readInt());
            if (ver != null) {//协议存在
                ByteBuf tmp = ctx.alloc().buffer();
                VerMessage message;
                try {
                    tmp.writeBytes(buf, read);
                    message = ver.getVerCoder().decode(tmp);
                } finally {
                    tmp.release();
                }
                out.add(message);
                handleVerMessage(message);
            }
        }
    }

    protected abstract void handleVerMessage(VerMessage message);
}
