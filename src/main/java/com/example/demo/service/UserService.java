package com.example.demo.service;

import com.example.demo.mybatis.UserMapper;
import com.example.demo.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    //@Autowired
    //private UserMapper userMapper;
    User findUserByUsername(String username);
    //public void findUserByUsername(String username) {
        //return userMapper.findUserByUsername(username);
    //}

}