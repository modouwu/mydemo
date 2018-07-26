package com.example.demo.proxy.staticProxy;

public class StudentsProxy implements Person{
    //被代理的学生
    Student stu;

    public StudentsProxy(Person stu) {
        // 只代理学生对象
        if(stu.getClass() == Student.class) {
            this.stu = (Student)stu;
        }
    }

    //代理上交班费，调用被代理学生的上交班费行为
    @Override
    public void giveMoney() {
        //代理模式就是在访问实际对象时引入一定程度的间接性，
        // 因为这种间接性，可以附加多种用途。这里的间接性就是指不直接调用实际对象的方法，那么我们在代理过程中就可以加上一些其他用途。
        //这种操作，也是使用代理模式的一个很大的优点。最直白的就是在Spring中的面向切面编程（AOP），
        // 我们能在一个切点之前执行一些操作，在一个切点之后执行一些操作，这个切点就是一个个方法
        System.out.println("张三最近学习有进步！");
        stu.giveMoney();
    }
}
