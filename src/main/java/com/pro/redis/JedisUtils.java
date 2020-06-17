package com.pro.redis;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

public class JedisUtils {
    private RedisTemplate<String,Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init(){
        System.out.println("jedisutil init");
    }


    public  boolean setString(String key,String value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public String getString(String key){
        try{
            return (String) redisTemplate.opsForValue().get(key);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    //事务开启之后，删除永远是 false 因为不知道最后是否commit删除
    public void delKey(String key){
        //事务开启
        redisTemplate.multi();
        try{
            boolean result = redisTemplate.delete(key);
            //提交事务，如果没执行此方法将不会对redis有操作
            redisTemplate.exec();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        //return false;
    }

    public void getif(String key){
        //事务开启
        redisTemplate.opsForValue().setIfAbsent("a","b");
    }

    public boolean isExistsKey(String key){
        return redisTemplate.hasKey(key);
    }

}
