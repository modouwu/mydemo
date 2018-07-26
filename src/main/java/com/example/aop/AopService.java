package com.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AopService {
    public String good(String name,int state){
        log.info("调用切入点方法成功,good!");
        return "name"+name+"---"+state;
    }
}
