package com.liyiruo.thread_local.controller;

/**
 * @author liyiruo
 * @data 2020/3/7  4:22 下午
 */
public class Val<T> {
    T v;

    public T getV() {
        return v;
    }

    public void setV(T v) {
        this.v = v;
    }
}
