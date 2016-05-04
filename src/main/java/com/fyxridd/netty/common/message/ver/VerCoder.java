package com.fyxridd.netty.common.message.ver;

import com.fyxridd.netty.common.message.MessageContent;
import io.netty.buffer.ByteBuf;

public interface VerCoder {
    /**
     * 编码
     * 把内容放入buf
     * @param content 内容
     */
    void encode(ByteBuf buf, MessageContent content);

    /**
     * 解码
     */
    VerMessage decode(ByteBuf buf, int read);
}
