package com.pro.zkTest;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZKTest2 {
    static ZooKeeper zk;
    static String datastr;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
         zk = new ZooKeeper("127.0.0.1:2181", 60 * 1000 * 3, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(event.getType().equals(Event.EventType.NodeDataChanged)){
                    try {
                      byte [] data  =  zk.getData("/appconifg/redisconfig/redis.properties",true,null);
                      datastr =  new String(data);
                      System.out.println(datastr);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("数据发生了改变，重新加载");
                }
            }
        });
        Stat stat =  zk.exists("/appconifg/redisconfig/redis.properties",false);
        System.out.println(stat);
        byte [] data  =  zk.getData("/appconifg/redisconfig/redis.properties",true,null);
        datastr =  new String(data);
        System.out.println(datastr);
        Thread.sleep(Long.MAX_VALUE);
    }
}
