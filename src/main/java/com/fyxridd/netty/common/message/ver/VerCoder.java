package com.fyxridd.netty.common.message.ver;

import io.netty.buffer.ByteBuf;

/**
 * 编码/解码器
 */
public interface VerCoder {
    /**
     * 编码
     */
    ByteBuf encode(VerMessage content);

    /**
     * 解码
     */
    VerMessage decode(ByteBuf buf);
}
