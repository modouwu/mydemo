package com.example.demo.myAnnotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Pointcut("@annotation(com.example.demo.myAnnotation.MyLog)")
    private void cut() { }

    /**
     * 定制一个环绕通知
     * @param joinPoint
     */
    @Around("cut()")
    public void advice(ProceedingJoinPoint joinPoint){
        System.out.println("环绕通知之开始");
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("环绕通知之结束");
    }

    @Before("cut()")
    public void before() {
        System.out.println("已经记录下操作日志@Before 方法执行前");
    }

    @After("cut()")
    public void after() {
        System.out.println("已经记录下操作日志@After 方法执行后");
    }
}