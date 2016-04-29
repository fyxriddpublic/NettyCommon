package com.fyxridd.netty.common.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import org.json.JSONObject;

public class MessageEncoder extends MessageToByteEncoder<JSONObject>{
    @Override
    protected void encode(ChannelHandlerContext ctx, JSONObject json, ByteBuf buf) throws Exception {
        byte[] bytes = json.toString().getBytes(CharsetUtil.UTF_8);
        //写入Head
        buf.writeInt(bytes.length);
        //写入Body
        buf.writeBytes(bytes);
    }
}
