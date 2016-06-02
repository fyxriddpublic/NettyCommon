package com.fyxridd.netty.common.packet;

import java.util.UUID;

/**
 * 包信息上下文
 */
public class PacketContext {
    private PacketType packetType;
    private int packetVer;
    private UUID uid;//(原样返回)
    private long time;//发出时间(原样返回)
    private Packet packet;

    public PacketContext() {
    }

    public PacketContext(PacketType packetType, int packetVer, UUID uid, long time, Packet packet) {
        this.packetType = packetType;
        this.packetVer = packetVer;
        this.uid = uid;
        this.time = time;
        this.packet = packet;
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public void setPacketType(PacketType packetType) {
        this.packetType = packetType;
    }

    public int getPacketVer() {
        return packetVer;
    }

    public void setPacketVer(int packetVer) {
        this.packetVer = packetVer;
    }

    public Packet getPacket() {
        return packet;
    }

    public void setPacket(Packet packet) {
        this.packet = packet;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
