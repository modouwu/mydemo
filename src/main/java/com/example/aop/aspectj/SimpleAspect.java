package com.example.aop.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

@Aspect
@Component
@Slf4j
@Order(value = 1)//当一个方法被多个切入点拦截到,通过这个指定执行顺序,值越小优先级越高
public class SimpleAspect {
    //执行顺序:
    // 一,around
    // 若在此执行了proceed()方法,则顺序执行before method--good after afterReturn
    // 否则,顺序执行after afterReturn

    //@Pointcut(value="execution( * com.example.aop.AopService.*(..))")
    @Pointcut(value="execution( String com.example.aop.AopService.good(String,int))||execution( String com.example.aop.AopService.*(..))")
    public void pointcutID() {
        // 这里的代码不执行,但是会作为切入点的id
        log.info("pointcut is running");
    }

    //joinPoint 代表了com.example.aop.AopService.good(String,int)这个方法对象,里面包含此方法的全部信息
    @Before(value = "pointcutID()")
    public void doBefore(JoinPoint joinPoint){
        log.info("now is before!");
        String name=joinPoint.getSignature().getName();//方法名 good
        String typeName=joinPoint.getSignature().getDeclaringTypeName();//类名 com.example.aop.AopService
        Object[] args=joinPoint.getArgs();//参数值列表 mike 9
        RequestAttributes attributes=RequestContextHolder.getRequestAttributes();
        String[] strings=attributes.getAttributeNames(0);
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)attributes;
        HttpServletRequest httpRequest=servletRequestAttributes.getRequest();
        log.info(httpRequest.getRequestURI());
        log.info(httpRequest.getRemoteAddr());
        Enumeration<String> enu=httpRequest.getParameterNames();

        while(enu.hasMoreElements()){//参数列表及值

            String paraName=(String)enu.nextElement();

            System.out.println(paraName+": "+httpRequest.getParameter(paraName));

        }
    }

    //在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
    //returning:该属性指定一个形参名，用于表示Advice方法中可定义与此同名的形参，该形参可用于访问目标方法的返回值。
    // 除此之外，在Advice方法中定义该形参（代表目标方法的返回值）时指定的类型，会限制目标方法必须返回指定类型的值或没有返回值。
    @AfterReturning(value = "pointcutID()",returning = "rtv")//rtv是方法的返回值或者around自定义的返回值
    public void doAfterReturn(JoinPoint joinPoint,String rtv){//joinPoint不管method是否执行,都代表method对象不变
        log.info("AfterReturning is running!");
        String name=joinPoint.getSignature().getName();
        String typeName=joinPoint.getSignature().getDeclaringTypeName();
        Object[] args=joinPoint.getArgs();
        RequestAttributes attributes=RequestContextHolder.getRequestAttributes();
        String[] strings=attributes.getAttributeNames(0);
        ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)attributes;
        HttpServletRequest httpRequest=servletRequestAttributes.getRequest();
        log.info(httpRequest.getRequestURI());
        log.info(httpRequest.getRemoteAddr());
        Enumeration<String> enu=httpRequest.getParameterNames();
        while(enu.hasMoreElements()){

            String paraName=(String)enu.nextElement();

            System.out.println(paraName+": "+httpRequest.getParameter(paraName));

        }
        log.info(rtv);

    }

    @After(value="pointcutID()")
    public void doAfter(JoinPoint joinPoint){
        log.info("after is running");
        String name=joinPoint.getSignature().getName();
        String typeName=joinPoint.getSignature().getDeclaringTypeName();
        Object[] args=joinPoint.getArgs();
    }

    @Around(value = "pointcutID()")//proceedingJoinPoint这个对象跟上面的JoinPoint一样 为啥换了名词
    public Object doRound(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object target=proceedingJoinPoint.getTarget();
        return proceedingJoinPoint.proceed();
        //return "zidongyi zhi";

    }

    @AfterThrowing(value = "pointcutID()")
    public void doAfterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect1] afterThrowing advise");
    }

    @AfterThrowing(value = "pointcutID()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
    {
        System.out.println("=====SysLogAspect异常通知开始=====");
        //handleLog(joinPoint, e);
    }

    @Pointcut(value = "execution(* com.example.aop.aspectj.jdkproxyIml.sayhi())")
    public void pointcut2(){

    }



    @After(value="pointcut2()")
    public void doAfter1(JoinPoint joinPoint){
        log.info("after is running");
        String name=joinPoint.getSignature().getName();
        String typeName=joinPoint.getSignature().getDeclaringTypeName();
        Object[] args=joinPoint.getArgs();
    }
}
