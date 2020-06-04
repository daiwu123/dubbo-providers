package com.pro.dubboservice;
import com.alibaba.dubbo.rpc.RpcContext;
import com.s.service.IPopService;
import org.apache.dubbo.config.annotation.Service;


/*protocol = {"dubbo","rmi"}*/
@Service(version = "0.1",group = "group2",loadbalance = "roundrobin")
public class PopServiceImpl2 implements IPopService {
    @Override
    public String helloword() {

        return "我是第二个实现->"+RpcContext.getContext().getLocalAddressString();
    }
}
