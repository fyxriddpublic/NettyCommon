package com.fyxridd.netty.common.ver;

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
     * 解码(不用负责释放buf)
     */
    VerMessage decode(ByteBuf buf);
}
