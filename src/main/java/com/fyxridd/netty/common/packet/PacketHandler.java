package com.fyxridd.netty.common.packet;

import io.netty.buffer.ByteBuf;

/**
 * 包处理器
 */
public interface PacketHandler<T extends Packet> {
    T fromByteBuf(ByteBuf buf);

    ByteBuf toByteBuf(T t);
}
