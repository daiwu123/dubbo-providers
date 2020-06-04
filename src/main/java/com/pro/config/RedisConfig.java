package com.pro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPoolConfig;

/*@Configuration
@PropertySource(value = "classpath:properties/redis.properties")
public class RedisConfig {
    private String maxIdle;
    private String maxTotal;

    @Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setMaxTotal();
    }
}*/
