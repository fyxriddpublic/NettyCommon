package com.fyxridd.netty.common.packet;

import java.util.HashMap;
import java.util.Map;

public class PacketType {
    private static Map<String, Map<Integer, PacketType>> ids = new HashMap<>();

    //包组
    private String packetGroup;
    //包ID
    private int packetId;

    private Class packetClass;
    private Map<Integer, PacketHandler> packetHandlers = new HashMap<>();
    private int highestPacketVer;//最高版本

    public PacketType(String packetGroup, int packetId, Class packetClass) {
        this.packetGroup = packetGroup;
        this.packetId = packetId;
        this.packetClass = packetClass;
        Map<Integer, PacketType> map = ids.get(packetGroup);
        if (map == null) {
            map = new HashMap<>();
            ids.put(packetGroup, map);
        }
        map.put(packetId, this);
    }

    public String getPacketGroup() {
        return packetGroup;
    }

    public int getPacketId() {
        return packetId;
    }

    public Class getPacketClass() {
        return packetClass;
    }

    /**
     * 获取版本最合适的包处理器
     * @param targetVer 目标版本
     * @return 如果目标版本的处理器存在则直接获取,否则获取最高版本的处理器,没有处理器返回null
     */
    public PacketHandler getPacketHandler(int targetVer) {
        PacketHandler result = packetHandlers.get(targetVer);
        if (result == null) result = packetHandlers.get(highestPacketVer);
        return result;
    }

    /**
     * 注册指定版本的包处理器(外部框架勿调用)
     * @param ver 版本
     * @param packetHandler 包处理器
     */
    public void registerPackerHandler(int ver, PacketHandler packetHandler) {
        if (packetHandlers.isEmpty() || ver > highestPacketVer) highestPacketVer = ver;
        packetHandlers.put(ver, packetHandler);
    }

    /**
     * 获取包类型
     * @return 不存在返回null
     */
    public static PacketType get(String packetGroup, int packetId) {
        Map<Integer, PacketType> map = ids.get(packetGroup);
        if (map != null) return map.get(packetId);
        return null;
    }
}
