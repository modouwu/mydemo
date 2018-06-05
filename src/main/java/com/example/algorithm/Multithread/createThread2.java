package com.example.algorithm.Multithread;

public class createThread2 extends Thread{
    private String name;
    public createThread2(String name) {
        this.name=name;
    }
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public static void main(String[] args) throws InterruptedException {
        createThread2 mTh1=new createThread2("A");
        createThread2 mTh2=new createThread2("B");
        mTh1.start();
        mTh2.start();
        mTh1.join();
        //注意：start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。
        //从程序运行的结果可以发现，多线程程序是乱序执行。因此，只有乱序执行的代码才有必要设计为多线程。
    }
}
