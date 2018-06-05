package com.example.demo;

import com.example.demo.mybatis.model.User;
import com.example.demo.service.UserService;
import com.example.zhujie.model.WiselySettings;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestUser {

    @Autowired
    private UserService userService;
    @Autowired
    WiselySettings wiselySettings;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    KeyGenerator keyGenerator;
    @Autowired
    JedisPool jedisPool;
    @Autowired
    ShardedJedisPool shardedJedisPool;
    @Test
    public void findUser(){
        User user = userService.findUserByUsername("testZhang");
        log.info("this user is "+"name"+user.getName()+"age"+user.getAge());
    }

    @Test
    public void test(){
        System.out.println(wiselySettings.getGender()+"---"+wiselySettings.getName()+"---"+wiselySettings.getAge());
    }

    @Test
    public void stringRedis(){
        log.info("set vale :key=work,value=hard");
        stringRedisTemplate.opsForValue().set("work","hard");
        log.info("get value is "+stringRedisTemplate.opsForValue().get("work"));
        stringRedisTemplate.opsForHash().put("hwork","work","hard");
        log.info("get hvalue is "+stringRedisTemplate.opsForHash().get("hwork","work"));
    }
    @Test
    public void redis(){
        log.info("set vale :key=workt,value=hardt");
        redisTemplate.opsForValue().set("workt",111);
        redisTemplate.opsForHash().put("hworkt",999,9990);
        log.info("get value is"+redisTemplate.opsForValue().get("workt"));
        log.info("get hvalue is"+redisTemplate.opsForHash().get("hworkt",999));
    }
    @Test
    public void jedisPool(){
        Jedis redis=jedisPool.getResource();
        redis.set("jwork","jhard");
        redis.hset("jhwork","jhworkt","jhardt");
        log.info("jwork is "+redis.get("jwork"));
        log.info("jwork is "+redis.hget("jhwork","jhworkt"));
        log.info("host is "+redis.getClient().getHost()+"port is :"+redis.getClient().getPort());
        redis.close();
    }
    @Test
    public void shardedJedisPool(){
        ShardedJedis jedis=shardedJedisPool.getResource();
        jedis.set("sjwork1","sjhard1");
        jedis.set("sjwork2","sjhard2");
        jedis.set("sjwork3","sjhard3");
        jedis.set("sjwork4","sjhard4");
        jedis.set("sjwork5","sjhard5");
        jedis.get("sjwork1");
        log.info("sjwork is "+jedis.get("sjwork"));
        log.info("host is "+jedis.getShard("sjwork1").getClient().getHost()+"port is :"+jedis.getShard("sjwork1").getClient().getPort());
        log.info("host is "+jedis.getShard("sjwork2").getClient().getHost()+"port is :"+jedis.getShard("sjwork2").getClient().getPort());
        log.info("host is "+jedis.getShard("sjwork3").getClient().getHost()+"port is :"+jedis.getShard("sjwork3").getClient().getPort());
        log.info("host is "+jedis.getShard("sjwork4").getClient().getHost()+"port is :"+jedis.getShard("sjwork4").getClient().getPort());
        log.info("host is "+jedis.getShard("sjwork5").getClient().getHost()+"port is :"+jedis.getShard("sjwork5").getClient().getPort());
        jedis.close();
    }
    @Test
    public void pipelined(){
        Pipeline pipeline=jedisPool.getResource().pipelined();
        ShardedJedisPipeline pipeline1=shardedJedisPool.getResource().pipelined();
        for (int i = 0; i <10; i++) {
            pipeline.set("pwork"+String.valueOf(i),String.valueOf(i));
            pipeline.hset("phwork","phwork"+String.valueOf(i),String.valueOf(i));
            pipeline1.set("pwork2"+String.valueOf(i),String.valueOf(i));
            pipeline1.hset("phwork2","phwork2"+String.valueOf(i),String.valueOf(i));
        }
        //Response<List<Object>>response=pipeline.exec();这个方法是另一种提交方式:事物
        List<Object> list=pipeline.syncAndReturnAll();//结果是1,ok,1,ok,...
        pipeline1.sync();
        List<Object> list1=pipeline1.getResults();
        List<Object> list2=pipeline1.syncAndReturnAll();
    }
}
