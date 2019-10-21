package com.lirui.threadpractice.practice.threadcommunication;

/**
 * @author lirui
 * @ClassName:
 * @Description: 三种线程通信方式
 * @date 2019/9/16 16:20
 */

import java.util.concurrent.locks.LockSupport;

/**
 * 三种线程协作通信的方式：suspend/resume、wait/notify、park/unpark
 */
public class threadpractice {
    public static Object stamobject = null;

    /*suspend/resume  正常通信*/
    public static void susResTrue() {
        Thread currsumThread = new Thread(() -> {
            if (stamobject == null) {//没有对象
                System.out.println("进入等待");
                Thread.currentThread().suspend();
            }
            System.out.println("有了对象，回家");
        });
        try {
            currsumThread.start();
            Thread.sleep(1000);
            stamobject = new Object();
            System.out.println("等待释放信号");
            currsumThread.resume();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*suspend/resume死锁*/
    public void susResDie() {
        Thread currsumThread = new Thread(() -> {
            if (stamobject == null) {
                System.out.println("进入等待");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.currentThread().suspend();

                System.out.println("有了对象，回家");
            }
        });
        currsumThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stamobject = new Object();
        System.out.println("等待信号");

        currsumThread.resume();

    }

    /*wait/notify正常通信*/
    public void waitNotify() {
        new Thread(() -> {
            if (stamobject == null) {

                synchronized (this) {
//                    System.out.println("");
                    System.out.println("进入等待");
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("拿到对象，带回家了");

        }).start();
        try {
            Thread.sleep(3000);
            synchronized (this) {
                System.out.println("准备唤起");
                this.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*wait/notify死锁*/

    public void waitNotifyDie() {
        new Thread(() -> {

            try {
                Thread.sleep(5000);
                if (stamobject == null) {
                    System.out.println("进入等待");
                    synchronized (this) {
                        this.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("拿到对象，带回家了");
        }).start();
        try {
            Thread.sleep(2000);
            System.out.println("准备唤起");
            synchronized (this) {
                this.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /*pack/unpack正常通信*/
    public void packUnpack() {
        Thread currentThread = new Thread(() -> {
            while (stamobject == null) {
                System.out.println("进入等待");
                LockSupport.park();
            }
            System.out.println("买到对象，回家");
        });
        currentThread.start();
        try {
            Thread.sleep(3000);
            System.out.println("准备唤起");
            stamobject = new Object();
            LockSupport.unpark(currentThread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void packUnpackDie() {
        Thread currentThread = new Thread(() -> {
            while (stamobject == null) {
                System.out.println("进入等待");
                synchronized (this) {
                    LockSupport.park();
                }
            }
            System.out.println("买到对象，回家");
        });
        currentThread.start();
        try {
            Thread.sleep(1000);
            System.out.println("准备唤起");
            synchronized (this) {
                LockSupport.unpark(currentThread);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new threadpractice().packUnpackDie();
    }
}
