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
        System.out.println("测试一次性的33333333糖糖修改");
        System.out.println("测试一次性的33333333daiwu修改");
        System.out.println("daiwu新加的逻辑");
        System.out.println("糖糖新加的逻辑");
    }

    @Test
    public void test2(){
        System.out.println("daiwu测试的文件");
    }
}
