package com.fyxridd.netty.common.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class Lv1Decoder extends ByteToMessageDecoder {
    private int read;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        //长度
        if (read == 0) {
            if (buf.readableBytes() < 2) return;
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
            ByteBuf tmp = ctx.alloc().buffer();
            tmp.writeBytes(buf, read);
            ctx.fireChannelRead(tmp);
        }
    }
}
