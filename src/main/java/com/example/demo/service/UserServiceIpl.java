package com.example.demo.service;

import com.example.demo.mybatis.UserMapper;
import com.example.demo.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceIpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "ddd")
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}
