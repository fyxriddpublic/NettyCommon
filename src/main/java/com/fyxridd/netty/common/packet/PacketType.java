package com.fyxridd.netty.common.packet;

import java.util.HashMap;
import java.util.Map;

public class PacketType {
    private static Map<Integer, PacketType> ids = new HashMap<>();

    private int packetId;
    private Class packetClass;
    private Map<Integer, PacketHandler> packetHandlers = new HashMap<>();

    public PacketType(int packetId, Class packetClass) {
        this.packetId = packetId;
        this.packetClass = packetClass;
        ids.put(packetId, this);
    }

    public int getPacketId() {
        return packetId;
    }

    public Class getPacketClass() {
        return packetClass;
    }

    public PacketHandler getPacketHandler(int ver) {
        return packetHandlers.get(ver);
    }

    /**
     * 注册指定版本的包处理器(外部框架勿调用)
     * @param ver 版本
     * @param packetHandler 包处理器
     */
    public void registerPackerHandler(int ver, PacketHandler packetHandler) {
        packetHandlers.put(ver, packetHandler);
    }

    public static PacketType getById(int packetId) {
        return ids.get(packetId);
    }
}
