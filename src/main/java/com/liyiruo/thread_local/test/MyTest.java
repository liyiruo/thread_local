package com.liyiruo.thread_local.test;

/**
 * @author liyiruo
 * @data 2020/3/7  5:16 下午
 */
public class MyTest {
    static MyThreadLocal<Long> v = new MyThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return Thread.currentThread().getId();
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(v.get());
            }).start();
        }
    }
}
