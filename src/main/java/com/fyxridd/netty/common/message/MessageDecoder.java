package com.fyxridd.netty.common.message;

import com.fyxridd.netty.common.MessageMain;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import org.json.JSONObject;

import java.util.List;

public abstract class MessageDecoder extends ByteToMessageDecoder{
    private int read;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        //Head: int,代表字节数
        if (read == 0) {
            if (buf.readableBytes() < 2) return;
            else {
                read = buf.readInt();
                //异常
                if (read <= 0) {
                    buf.release();
                    ctx.close();
                }
            }
        }

        //Body
        if (buf.readableBytes() >= read) {
            JSONObject json;
            ByteBuf b = ctx.alloc().buffer(read);
            try {
                buf.writeBytes(b);
                json = new JSONObject(b.toString(CharsetUtil.UTF_8));
            } finally {
                b.release();
            }
            out.add(json);
            //处理
            handle(MessageMain.getMessageManager().fromJson(json));
        }
    }

    protected abstract void handle(Message msg);
}
