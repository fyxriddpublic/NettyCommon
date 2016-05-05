package com.fyxridd.netty.common;

public interface Listener<T extends Message> {
    void onEvent(T msg);
}
