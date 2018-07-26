package com.example.lambda;

public class TestInterface {
    interface MyInterface {
        void hello();
    }

    interface MyInterface2 {
        void add(int x, int y);
    }

    interface MyInterface3 {
        void print(int x);
    }

    public static void main(String[] args) {
        MyInterface infVar = () -> {
            System.out.println("Hello World");
        };
        infVar.hello();
        infVar = () -> {
            System.out.println("Hello World,second");
        };
        infVar.hello();//同一个借口可以在运行时有不同的方法体

        MyInterface2 infVar2 = (x, y) -> {
            System.out.println("x+y=" + (x + y));
        };
        infVar2.add(5, 10);

        MyInterface3 infVar3 = (x)->{
            System.out.println(x);
        };
        infVar3.print(3333);
    }

}
