package com.fyxridd.netty.common.ver;

/**
 * 信息
 */
public abstract class VerMessage {
    protected Ver ver;

    public VerMessage(Ver ver) {
        this.ver = ver;
    }

    public Ver getVer() {
        return ver;
    }
}
