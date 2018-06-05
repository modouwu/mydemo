package com.example.demo.listener;

import com.example.demo.mybatis.model.User;
import org.springframework.context.ApplicationEvent;

public class UserRegisterEvent extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    //source参数指的是发生事件的对象
    public UserRegisterEvent(Object source,User user) {
        super(source);
        this.user=user;
    }

    public User getUser() {
        return user;
    }

    //注册用户对象
    private User user;
}
