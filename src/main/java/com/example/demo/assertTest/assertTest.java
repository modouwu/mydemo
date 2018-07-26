package com.example.demo.assertTest;

public class assertTest {
    public static void main(String[] args){
        boolean s=false;
        /*assert s;
        System.out.println("断言1没有问题，Go！");*/
        assert s:"断言失败";
        System.out.println("断言2没有问题，Go！");
    }
}
