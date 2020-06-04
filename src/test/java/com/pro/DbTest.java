package com.pro;

import com.pro.pj.Money;
import com.pro.redis.JedisUtils;
import com.pro.sql.mapper.MoneyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class DbTest {
    @Autowired
    private MoneyMapper moneyMapper;
    @Autowired
    private JedisUtils jedisUtils;

    @Test
    public void moneyUpdateTest(){
        moneyMapper.lessMoney(new Money(1,2));
    }

    @Test
    public void redisTest(){
        jedisUtils.setString("","hi 这个是我第次");
    }

    @Test
    public void getRedisTest(){
        System.out.println(jedisUtils.getString("A服务器"));
    }

    @Test
    public void deleteRedisTest(){
        System.out.println("删除了无用的输出");
        jedisUtils.delKey("dada");
        System.out.println("dasdadrtyua");
    }

}
