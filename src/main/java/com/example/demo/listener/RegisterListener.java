package com.example.demo.listener;

import com.example.demo.mybatis.model.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component//需要使用@Component注解来声明该监听需要被Spring注入管理
public class RegisterListener implements ApplicationListener<UserRegisterEvent> {

    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        //获取注册用户对象
        User user = event.getUser();

        //../省略逻辑

        //输出注册用户信息
        System.out.println("implements ApplicationListener注册信息，用户名："+user.getName()+"，性别："+user.getSex());
    }
}
