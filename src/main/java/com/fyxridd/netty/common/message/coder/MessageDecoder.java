package com.fyxridd.netty.common.message.coder;

import com.fyxridd.netty.common.message.ver.Ver;
import com.fyxridd.netty.common.message.ver.VerMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @see MessageEncoder
 */
public abstract class MessageDecoder extends ByteToMessageDecoder{
    private int read;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        //Head
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

        //Body
        if (buf.readableBytes() >= read) {
            Ver ver = Ver.getVerByNum(buf.readInt());
            if (ver != null) {
                VerMessage message = ver.getVerCoder().decode(buf, read);
                out.add(message);
                handleVerMessage(message);
            }
        }
    }

    protected abstract void handleVerMessage(VerMessage message);
}
