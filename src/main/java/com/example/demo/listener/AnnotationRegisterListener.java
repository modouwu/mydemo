package com.example.demo.listener;

import com.example.demo.mybatis.model.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotationRegisterListener {
    @EventListener//方式一 注解监听
    public void register(UserRegisterEvent userRegisterEvent)
    {
        //获取注册用户对象
        User user = userRegisterEvent.getUser();

        //../省略逻辑

        //输出注册用户信息
        System.out.println("@EventListener注册信息，用户名："+user.getName()+"，性别："+user.getSex());
    }
}
