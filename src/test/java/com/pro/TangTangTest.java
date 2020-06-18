package com.pro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TangTangTest {
    @Test
    public void test1(){
        System.out.println("糖糖的测试开始了..");
        System.out.println("戴武新加的逻辑");
    }
}
