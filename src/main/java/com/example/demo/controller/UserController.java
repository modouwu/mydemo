package com.example.demo.controller;

import com.example.demo.listener.UserRegisterEvent;
import com.example.demo.listener.UserServicePublish;
import com.example.demo.mybatis.UserMapper;
import com.example.demo.mybatis.model.User;
import com.example.demo.service.UserService;
import com.example.zhujie.model.WiselySettings;
import com.example.zhujie.model.WiselySettings2;
import com.example.zhujie.model.WiselySettings3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserServicePublish userServicePublish;

    @Autowired
    WiselySettings wiselySettings;
    @Resource
    WiselySettings2 wiselySettings2;
    @Resource
    WiselySettings3 wiselySettings3;

    @RequestMapping("/userTEST")
    @ResponseBody
    public User getUserInfoByName(String name){
        User user = userService.findUserByUsername("testZhang");
        System.out.println(user);
        return user;
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> queryUserById(@PathVariable("name") String id) {
        try {
            User user = this.userService.findUserByUsername(id);
            if (null == user) {
                // 资源不存在，响应404
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            // 200
            // return ResponseEntity.status(HttpStatus.OK).body(user);
            ResponseEntity responseEntity=ResponseEntity.ok(user);
            ResponseEntity responseEntity2 = new ResponseEntity<>(user,HttpStatus.ACCEPTED);
            ResponseEntity.noContent().build();
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @RequestMapping("/indextest")
    public String indexPage (){
        return "index";
    }

    @RequestMapping("/address")
    public String address (){
        return "address";
    }

    //通过调用注册发布事件
    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(User user){
        //调用注册业务逻辑
        userServicePublish.register(user);
        return "注册成功.";
    }

    //不发布只调用
    @RequestMapping(value = "/register2")
    @ResponseBody
    public String register2(User user){
        //监听不到,必须publish才能监听到,publish不是持续生效的,只能监听一次
        new UserRegisterEvent(this,user);
        return "注册成功.";
    }


    //不发布只调用
    @RequestMapping(value = "/http/filter")
    @ResponseBody
    public String inceptor(){
        //监听不到,必须publish才能监听到,publish不是持续生效的,只能监听一次
        return "index";
    }


    //不发布只调用
    @RequestMapping(value = "/hello")
    @ResponseBody
    public WiselySettings hello(){
        System.out.println("now it is hello");
        //监听不到,必须publish才能监听到,publish不是持续生效的,只能监听一次
        return wiselySettings;
    }

    //不发布只调用
    @RequestMapping(value = "/hello2")
    @ResponseBody
    public WiselySettings2 hello1(){
        System.out.println("now it is hello");
        //监听不到,必须publish才能监听到,publish不是持续生效的,只能监听一次
        return wiselySettings2;
    }

    //不发布只调用
    @RequestMapping(value = "/hello3")
    @ResponseBody
    public WiselySettings3 hello3(){
        System.out.println("now it is hello");
        //监听不到,必须publish才能监听到,publish不是持续生效的,只能监听一次
        return wiselySettings3;
    }

}
