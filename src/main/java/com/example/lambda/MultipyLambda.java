package com.example.lambda;

import org.thymeleaf.expression.Maps;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MultipyLambda {
    public static void main(String[] args){
        MultipyLambda multipyLambda=new MultipyLambda();
        multipyLambda.summaryStatisticsTest();
        /*List<String> languages = Arrays.asList("Java","Python","scala","Shell","R");
        System.out.println("Language starts with J: ");
        multipyLambda.filterTest2(languages,x -> x.startsWith("J"));*/
    }
    //1.代替匿名内部类,因为内部方法是唯一的,所以lambda不需要考虑方法名
    public void oldRunable() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("hans");
            }
        }).start();
        new Thread(()->System.out.println("lambda")).start();
    }
    //2.集合遍历,map遍历
    public void traversalList(){
        List<String> list= Arrays.asList("a","b","c");
        for(String obj:list){
            System.out.println(obj);
        }
        list.forEach((x)->{System.out.println(x);});
        list.forEach(System.out::println);
    }
    public void traversalMap(){
        Map<String,String> map=new HashMap<String,String>(){
            {
                put("420001056337","X00241003");
                put("420001056321","X00241004");
                put("420001056338","X00241005");
                put("420001056322","X00241006");
                put("420001056341","X00241009");
            }
        };
        for (Map.Entry<String,String> entry:map.entrySet()){
            System.out.println(entry.getKey()+entry.getValue());
        }
        map.forEach((x,y)->System.out.println(x+y));
    }
    //3.map,将一个对象变成另一个对象,当方法体只有一个语句,可以省略return
    public void mapTest(){
        List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
        List<String> costs2 = Arrays.asList("1", "2","3");
        cost.stream().map((x)->x*10).forEach(System.out::println);
        List<Double> cost2=cost.stream().map((x)->x*10).collect(Collectors.toList());
        String cost3=costs2.stream().map((x)->x+"s").collect(Collectors.joining("-"));
        cost2.forEach(System.out::println);
    }
    //reduce将所有值合并为一个
    public void reduceTest(){
        List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
        Double xSum=cost.stream().reduce((sum,x)->sum+=x).get();
        System.out.println(xSum);
    }
    //filter过滤值,需要一个boolean表达式.可以与predicate配合
    public void filterTest(){
        List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
        cost=cost.stream().filter(x->x>15.0).collect(Collectors.toList());
        cost.forEach(System.out::println);
    }
    public static void filterTest2(List<String> languages, Predicate<String> condition) {
        languages.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + " "));
    }
    //IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用的方法叫做
    // summaryStatistics()
    public void summaryStatisticsTest(){
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }
}
