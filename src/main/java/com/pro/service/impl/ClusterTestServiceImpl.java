package com.pro.service.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.s.service.ClusterTestService;
import org.apache.dubbo.config.annotation.Service;

@Service(loadbalance = "roundrobin")
public class ClusterTestServiceImpl implements ClusterTestService {
    @Override
    public String getIP() {
        return "pro-ip"+ RpcContext.getContext().getLocalAddressString();
    }
}
