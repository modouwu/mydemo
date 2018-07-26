package com.example.lambda;

import java.util.Date;
import java.util.function.*;

public class TestCoreInterface {
    public static void main(String[] args) {
        Consumer<String> con=(str)->System.out.println(str);
        con.accept("我是消费型接口!");

        Supplier<Date> supp=()-> new Date();
        Date date=supp.get();
        System.out.println("供给型接口--当前时间:"+date);

        Function<String, String> fun=(str)->"hello,函数型接口,"+str;
        String str=fun.apply("xxx");
        System.out.println(str);

        Predicate<Integer> pre=(num)->num>0;
        Boolean flag=pre.test(10);
        System.out.println("断定型接口"+flag);
    }
}
