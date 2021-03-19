package com.zero.travel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Redis配置
 * @author LJC
 * @version 1.0
 * @date 2021/3/2 20:49
 */
//@Configuration
public class RedisConfig {

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);

        return stringRedisTemplate;
    }
}
