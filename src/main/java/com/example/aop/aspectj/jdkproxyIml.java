package com.example.aop.aspectj;

import org.springframework.stereotype.Service;

@Service
public class jdkproxyIml{
   // @Override
    public void sayhi() {
        System.out.print("hi");
    }
}
