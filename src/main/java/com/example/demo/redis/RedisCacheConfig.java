package com.example.demo.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class RedisCacheConfig {
    @Bean
    //缓存管理器
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheManager cacheManager=RedisCacheManager.create(factory);
        //EhCacheCacheManager ehCacheCacheManager=new EhCacheCacheManager();
        return cacheManager;
    }

    @Bean
    //当redis作为缓存使用时,可以通过本方法指定key的生存策略,如果不指定,默认使用SimpleKeyGenerator
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    @Bean
    //StringRedisTemplate,模板使用自带模板,只是连接不同
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate redisTemplate=new StringRedisTemplate(factory);
        //redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> template = new RedisTemplate<>();
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setConnectionFactory(factory);
        //key序列化方式:redisSerializer则key必须为字符串,不然会发生转换错误
        template.setKeySerializer(redisSerializer);
        // value序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //value hashmap序列化
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    public RedisConnectionFactory factory(){
        JedisConnectionFactory factory=new JedisConnectionFactory();
        factory.setHostName("127.0.0.1");
        factory.setPort(6379);
        return factory;
    }

    //jedis是redis的客户端,redis有很多客户端
    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig){
        return new JedisPool(jedisPoolConfig,"127.0.0.1",6379);
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);//最大连接数
        jedisPoolConfig.setMaxIdle(50);//最大空闲数
        jedisPoolConfig.setMaxWaitMillis(1000);//最大建立链接等待时间
        return jedisPoolConfig;
    }

    //sharejedispool 分布式共享jedispool
    @Bean
    public ShardedJedisPool shardedJedisPool(JedisPoolConfig jedisPoolConfig){
        JedisShardInfo jedisShardInfo=new JedisShardInfo("127.0.0.1",6379,500);
        JedisShardInfo jedisShardInfo2=new JedisShardInfo("127.0.0.1",6380,500);
        JedisShardInfo jedisShardInfo3=new JedisShardInfo("127.0.0.1",6381,500);
        //jedisShardInfo.setPassword("666");
        List<JedisShardInfo> shardInfoList=new ArrayList<>();
        shardInfoList.add(jedisShardInfo);
        shardInfoList.add(jedisShardInfo2);
        shardInfoList.add(jedisShardInfo3);
        ShardedJedisPool shardedJedisPool=new ShardedJedisPool(jedisPoolConfig,shardInfoList);
        return shardedJedisPool;
    }

}
