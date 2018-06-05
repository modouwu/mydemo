package com.example.algorithm.Multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

public class createThread3 implements Callable {

    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 140; i++) {
            System.out.println(Thread.currentThread().getName() + "运行  :  " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Callable oneCallable = new createThread3();
        FutureTask oneTask = new FutureTask(oneCallable);
        Thread oneThread = new Thread(oneTask,"a");
        Thread oneThread2 = new Thread(oneTask,"b");
        oneThread.start();
        oneThread2.start();
    }
}
