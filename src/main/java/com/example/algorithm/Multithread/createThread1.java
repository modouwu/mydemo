package com.example.algorithm.Multithread;

public class createThread1 implements Runnable{
    private String name;

    public createThread1(String name) {
        this.name=name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        new Thread(new createThread1("C")).start();
        new Thread(new createThread1("D")).start();
    }
}
