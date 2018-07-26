package com.example.demo.myAnnotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class JsonRest {

    @Resource
    TestRunner testRunner;
    @RequestMapping("/log")
    public String getLog(){
        testRunner.Run();
        return "<h1>Hello World</h1>";
    }
}