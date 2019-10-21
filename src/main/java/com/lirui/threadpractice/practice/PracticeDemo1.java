package com.lirui.threadpractice.practice;

/**
 * @author lirui
 * @ClassName: 线程状态
 * @Description: 状态实现
 * @date 2019/9/5 16:21
 */
public class PracticeDemo1 {
    public static void main(String[] args) throws InterruptedException {
// 线程第一种状态切换 新建---运行--终止
        System.out.println("线程第一种状态切换，新建---运行--终止");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1线程当期执行状态-=-=-=-=" + Thread.currentThread().getState().toString());
                System.out.println("thread1线程执行了");
            }
        });
        System.out.println("没调用start方法thread1状态" + thread1.getState());
        thread1.start();
        Thread.sleep(2000);
        System.out.println("线程执行完成后的thread1的状态" + thread1.getState());

//        线程第二种状态切换 新建--运行--等待--运行--终止
        System.out.println("线程第二种状态切换 新建--运行--等待--运行--终止");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2当前运行状态" + Thread.currentThread().getState());
                System.out.println("thread2开始执行了");
            }
        });
        System.out.println("thread2未开始前的状态" + thread2.getState());
        thread2.start();
        System.out.println("调用start方法thread2的状态" + thread2.getState());
        Thread.sleep(200);
        System.out.println("再等200毫秒，thread2的状态" + thread2.getState());
        Thread.sleep(3000);
        System.out.println("等三秒，thread2的状态" + thread2.getState());

        System.out.println("第三种运行状态 新建--运行--阻塞--运行--终止");
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (PracticeDemo1.class) {
                    System.out.println("thread3当前运行状态" + Thread.currentThread().getState());
                    System.out.println("开始执行了");
                }
            }
        });

        synchronized (PracticeDemo1.class) {
            System.out.println("thread3未执行时的状态" + thread3.getState());
            thread3.start();
            System.out.println("调用start方法后的thread3的状态" + thread3.getState());
            Thread.sleep(200);
            System.out.println("睡眠200毫秒后thread2的状态" + thread3.getState());
        }
        Thread.sleep(3000);
        System.out.println("等待3秒，让线程抢到锁，thread3状态" + thread3.getState());

    }
}
