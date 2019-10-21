package com.lirui.threadpractice.practice.threadcommunication;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

/**
 * @author lirui
 * @ClassName:
 * @Description:
 * @date 2019/10/17 10:26
 */
public class Demo7Test {
    public static ThreadLocal<String> local=new ThreadLocal<>();

    public static void main(String[] args) {
        local.set("这是主线程设置的值：123");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("主线程的值"+local.get());
                local.set("子线程的值456");
                System.out.println(local.get());
            }
        }).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("获取主线程的值"+local.get());
    }
}
