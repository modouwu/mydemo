package com.example.demo.netty.exam1;

public class HelloServiceImpl implements HelloService {
    public String hello(String msg) {
        return msg != null ? msg + " -----> I am fine." : "I am fine.";
    }
}
