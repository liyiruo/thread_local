package com.liyiruo.thread_local.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashSet;

/**
 * 这个命令可以压力测试 执行10000 开启10条线程  请求地址：localhost:8080/add
 * ab -n 10000 -c 10 localhost:8080/add
 *
 * @author liyiruo
 * @data 2020/3/7  3:25 下午
 */
@RestController
@Slf4j
public class StartController {

    static HashSet<Val<Integer>> set = new HashSet<>();

    synchronized static void addSet(Val<Integer> v) {
        set.add(v);
    }

    static ThreadLocal<Val<Integer>> c = new ThreadLocal<Val<Integer>>() {
        @Override
        protected Val<Integer> initialValue() {
            Val<Integer> v = new Val<>();
            v.setV(0);
            addSet(v);
            return v;
        }
    };

    void _add() throws InterruptedException {
        Thread.sleep(100);
        Val<Integer> v = c.get();
        v.setV(v.getV() + 1);
    }


    @RequestMapping("/stat")
    public Integer start() {
        return set.stream().map(x -> x.getV()).reduce((a, x) -> a + x).get();
    }

    @RequestMapping("/add")
    public Integer add() throws InterruptedException {
        _add();
        log.info("{}", c);
        return 1;
    }
}
