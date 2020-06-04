package com.pro.service.impl;

import com.s.service.EventService;
import org.apache.dubbo.config.annotation.Service;

@Service(loadbalance = "roundrobin")
public class EventServiceImpl implements EventService {


    @Override
    public String send(String ip) {
        return "发送的IP地址:--->"+ip;
    }
}
