package com.lirui.threadpractice.practice.practice2;

import javafx.scene.paint.Stop;

/**
 * @author lirui
 * @ClassName:
 * @Description:
 * @date 2019/9/11 16:20
 */
public class StopThread extends Thread {
    private int i, j;
    @Override
    public void run() {
       i=++i;
       synchronized (this){
           try {
               Thread.sleep(100000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           ++j;
       }

    }
    public void print(){
        System.out.println("i-=-=-=-"+i);
        System.out.println("j-=-=-=-=-="+j);
    }
}
