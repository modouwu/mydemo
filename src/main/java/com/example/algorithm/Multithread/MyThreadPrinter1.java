package com.example.algorithm.Multithread;

import lombok.Synchronized;

public class MyThreadPrinter1 implements Runnable {
    private String name;
    private Object object;

    private MyThreadPrinter1(String name, Object object) {
        this.name = name;
        this.object = object;
    }
    @Override
    public void run() {
        int count = 10;
        while (count > 0) {
            synchronized (object){
                System.out.print(name);
                count--;
                object.notify();
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        MyThreadPrinter1 pa = new MyThreadPrinter1("A", a);
        MyThreadPrinter1 pb = new MyThreadPrinter1("B", a);


        new Thread(pa).start();
        Thread.sleep(100);  //确保按顺序A、B执行
        new Thread(pb).start();
        Thread.sleep(100);
    }
}
