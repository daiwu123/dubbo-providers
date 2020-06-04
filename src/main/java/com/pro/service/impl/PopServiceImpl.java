package com.pro.service.impl;


import com.alibaba.dubbo.rpc.RpcContext;
import com.m.model.Pop;
import com.pro.sql.mapper.PopMapper;
import com.s.service.IPopService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//version :->接口不兼容的情况下使用version
//group ->当一个接口有多个实现，可以用分组区分
//delay -> 延迟暴露服务；0表示只要PopServiceImpl对象生成就暴露服务，
// -1表示等待spring容器初始化完毕；其他值表示对象生成之后多久暴露服务
@Service(version = "0.1",group = "group1",delay = -1,
        path="redis-service",timeout=5000,loadbalance = "roundrobin")
public class PopServiceImpl implements IPopService {

    @Autowired
    PopMapper popMapper;

    @Override
    public String asyncall() {
        System.out.println("开始执行异步测试方法");
        try {
            Thread.sleep(4000);
            System.out.println("进入异步测试方法了....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "异步返回的值..";
    }

    @Override
    public String helloword() {
        return "访问的提供者IP地址---->"+ RpcContext.getContext().getLocalAddressString();
    }

    @Override
    public void helloword(String s) {
        System.out.println("开始elloword。。");
        try {
            Thread.sleep(3000);
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束helloword。。");
    }

    @Override
    public List<Pop> query() {
        return popMapper.queryAllPop();
    }
}
