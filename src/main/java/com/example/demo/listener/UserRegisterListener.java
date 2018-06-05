package com.example.demo.listener;

import com.example.demo.mybatis.model.User;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
//监听是无序的,这个借口可以设置顺序
public class UserRegisterListener implements SmartApplicationListener {
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType==UserRegisterEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType== UserServicePublish.class;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //转换事件类型
        UserRegisterEvent userRegisterEvent = (UserRegisterEvent) event;
        //获取注册用户对象信息
        User user = userRegisterEvent.getUser();
        //.../完成注册业务逻辑
        System.out.println("注册信息，用户名："+user.getName()+"，性别："+user.getSex());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
