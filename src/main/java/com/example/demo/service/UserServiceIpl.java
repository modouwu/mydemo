package com.example.demo.service;

import com.example.demo.mybatis.UserMapper;
import com.example.demo.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "ddd")
public class UserServiceIpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    //@Cacheable(value = "ddd",key = "#username")
    @Cacheable
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
    @CacheEvict(value = "ddd",key = "#username")
    public void updateUserByUsername(String username,String username2){
        userMapper.updateUserByUsername(username,username2);
    }
    @CachePut(value = "ddd",key = "#username")
    //同步更新数据库和缓存
    public Integer updateUserByUsername2(String username,String age){
        return userMapper.updateUserByUsername2(username,age);
    }
    @Caching(cacheable = {@Cacheable(value = "ddd",key = "#username"),
            @Cacheable(value = "ddd",key = "hahaha")})
    //可以同事缓存多个键值,或者同时同步更新多个键值
    public Integer updateUserByUsername3(String username,String age){
        return userMapper.updateUserByUsername2(username,age);
    }
}
