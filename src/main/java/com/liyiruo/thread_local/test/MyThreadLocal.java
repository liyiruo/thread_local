package com.liyiruo.thread_local.test;

import lombok.var;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liyiruo
 * @data 2020/3/7  5:18 下午
 */
public class MyThreadLocal<T> {
    static AtomicInteger atomic = new AtomicInteger();
    Integer threadLocalHash = atomic.addAndGet(0x61c88647);

    static HashMap<Thread, HashMap<Integer, Object>> threadLocalMap = new HashMap<>();

    synchronized static HashMap<Integer, Object> getMap() {
        var thread = Thread.currentThread();
        if (!threadLocalMap.containsKey(thread)) {
            threadLocalMap.put(thread, new HashMap<Integer, Object>());
        }
        return threadLocalMap.get(thread);
    }

    protected T initialValue() {
        return null;
    }


    public T get() {
        var map = getMap();
        if (!map.containsKey(this.threadLocalHash)) {
            map.put(this.threadLocalHash, initialValue());
        }
        return (T) map.get(this.threadLocalHash);
    }

    public void set(T v) {
        var map = getMap();
        map.put(this.threadLocalHash, v);
    }
}
