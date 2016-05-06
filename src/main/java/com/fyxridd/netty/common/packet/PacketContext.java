package com.fyxridd.netty.common.packet;

/**
 * 包信息上下文
 */
public class PacketContext {
    private PacketType packetType;
    private int packetVer;
    private Packet packet;

    public PacketContext() {
    }

    public PacketContext(PacketType packetType, int packetVer, Packet packet) {
        this.packetType = packetType;
        this.packetVer = packetVer;
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
}
