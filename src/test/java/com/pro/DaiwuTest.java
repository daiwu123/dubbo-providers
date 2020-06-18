package com.pro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class DaiwuTest {
    @Test
    public void test1(){
        System.out.println("测试一次性的");
    }

    @Test
    public void test2(){
        System.out.println("daiwu测试的文件");
    }
}
