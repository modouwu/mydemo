package com.example.demo.listener;

import com.example.demo.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UserServicePublish {
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 用户注册方法
     * @param user
     */
    public void register(User user)
    {
        //../省略其他逻辑
//事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
        //发布UserRegisterEvent事件
        applicationContext.publishEvent(new UserRegisterEvent(this,user));
    }
}
