package com.fyxridd.netty.common.ver;

import com.fyxridd.netty.common.MessageContent;
import io.netty.buffer.ByteBuf;

/**
 * 编码/解码器
 */
public interface VerCoder<T extends MessageContent> {
    /**
     * 编码
     */
    ByteBuf encode(T content);

    /**
     * 解码(不用负责释放buf)
     */
    T decode(ByteBuf buf);
}
