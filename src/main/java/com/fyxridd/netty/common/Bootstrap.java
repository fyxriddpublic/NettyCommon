package com.fyxridd.netty.common;

import com.fyxridd.netty.common.packet.packets.Def;
import com.fyxridd.netty.common.util.Util;

public class Bootstrap {
    private static boolean enable;

    /**
     * 使用此库前必须调用此方法(可多次调用)
     */
    public static void bootstrap() {
        if (enable) return;
        synchronized (Bootstrap.class) {
            if (enable) return;
            enable = true;
        }

        for (Class c: Util.getClasses(Def.class.getPackage().getName())) {
            try {
                Class.forName(c.getName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
