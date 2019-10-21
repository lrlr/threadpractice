package com.lirui.threadpractice.practice.practice2;

import javafx.scene.paint.Stop;

/**
 * @author lirui
 * @ClassName:
 * @Description:
 * @date 2019/9/11 16:23
 */
public class StopThreadDemo {
    public static void main(String[] args) {
        StopThread stopThread=new StopThread();
        stopThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        stopThread.stop();//错误的终止方式
        stopThread.interrupt();//正确的终止方式
        while (stopThread.isAlive()){
              //确保线程已经终止
        }
        stopThread.print();
    }
}
