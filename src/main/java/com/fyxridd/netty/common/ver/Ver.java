package com.fyxridd.netty.common.ver;

import com.fyxridd.netty.common.ver.v1.Ver1Coder;

import java.util.HashMap;

/**
 * (应用层)协议
 */
public enum Ver {
    /**
     * Json协议
     */
    Json(1, new Ver1Coder())
    ;

    private static HashMap<Integer, Ver> vers = new HashMap<>();
    static {
        vers.put(1, Ver.Json);
    }

    //协议号
    private int num;
    //编码/解码器
    private VerCoder verCoder;

    Ver(int num, VerCoder verCoder) {
        this.num = num;
        this.verCoder = verCoder;
    }

    public int getNum() {
        return num;
    }

    public VerCoder getVerCoder() {
        return verCoder;
    }

    /**
     * 使用协议号获取协议
     * @return 不存在返回null
     */
    public static Ver getVerByNum(int num) {
        return vers.get(num);
    }
}
