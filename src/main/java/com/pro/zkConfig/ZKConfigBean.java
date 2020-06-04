package com.pro.zkConfig;

import com.m.config.RedisConfig;
import com.m.config.ZKConfig;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration*/
public class ZKConfigBean {

    private static final String configRootNode = "config";
    private static final String configGloablNode = "gloabl";
    @Bean
    public ZooKeeper zKBean(){
        ZooKeeper zk=null;
        try {
             zk = new ZooKeeper(ZKConfig.zkAddress, ZKConfig.sessionTimeout, new ZKWatcher());
             //如果zk没有连接上，重试，继续连接
             while(!zk.getState().isConnected()){
                 Thread.sleep(3000);
             }
             Stat stat = zk.exists("/"+ZKConfig.configRootNode+"/"+ZKConfig.configGloablNode,false);
             if(stat==null){
                 zk.create("/"+configRootNode+"/"+ZKConfig.configGloablNode,"app all gloabl properties".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
             }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return zk;
    }
    @Bean
    public RedisConfig redisConfigBean(){
        return new RedisConfig();
    }
}
