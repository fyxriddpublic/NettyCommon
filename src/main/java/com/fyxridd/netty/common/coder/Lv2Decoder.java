package com.fyxridd.netty.common.coder;

import com.fyxridd.netty.common.MessageContext;
import com.fyxridd.netty.common.MessageMain;
import com.fyxridd.netty.common.ver.Ver;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class Lv2Decoder extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;

            try {
                //ver
                Ver ver = Ver.getVerByNum(buf.readInt());
                if (ver != null) {//协议存在
                    //namespace
                    String namespace;
                    {
                        int length = buf.readInt();
                        byte[] bytes = new byte[length];
                        buf.readBytes(bytes, 0, length);
                        namespace = new String(bytes);
                    }
                    //name
                    String name;
                    {
                        int length = buf.readInt();
                        byte[] bytes = new byte[length];
                        buf.readBytes(bytes, 0, length);
                        name = new String(bytes);
                    }
                    //内容
                    MessageContext messageContext = new MessageContext(ver, namespace, name, ver.getVerCoder().decode(buf));
                    //触发事件
                    MessageMain.getMessageManager().trigger(messageContext);
                }
            } finally {
                buf.release();
            }
        }
    }
}
