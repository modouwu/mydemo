package com.example.aop;

import com.example.aop.aspectj.jdkproxyIml;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class aopController {
    @Resource
    private AopService aopService;
    @Resource
    private jdkproxyIml jdkproxy;

    @RequestMapping("/com/example/aop/aspectj")
    @ResponseBody
    public String hello(String name,int state){
        System.out.println(aopService.getClass());
        return aopService.good(name,state);

    }

    @RequestMapping("/com/example/aop/proxy/jdk")
    @ResponseBody
    public void hi(){
        jdkproxy.sayhi();
        System.out.println(jdkproxy.getClass());
    }
}
