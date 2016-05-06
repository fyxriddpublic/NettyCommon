package com.fyxridd.netty.common.packet;

import com.fyxridd.netty.common.packet.packets.basic.Packet1ServerInfo;

import java.util.HashMap;
import java.util.Map;

public enum PacketType {
    ServerInfo(1, Packet1ServerInfo.class)
    ;

    private static Map<Integer, PacketType> ids = new HashMap<>();
    static {
        ids.put(PacketType.ServerInfo.getPacketId(), PacketType.ServerInfo);
    }

    private int packetId;
    private Class packetClass;
    private Map<Integer, PacketHandler> packetHandlers = new HashMap<>();

    PacketType(int packetId, Class packetClass) {
        this.packetId = packetId;
        this.packetClass = packetClass;
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
