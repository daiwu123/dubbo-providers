package com.pro.zkTest;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class ZKTest1 {
    static ZooKeeper zk;
    static String datastr;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
         zk = new ZooKeeper("127.0.0.1:2181", 60 * 1000 * 3, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(event.getType().equals(Event.EventType.NodeDataChanged)){

                     /* byte [] data  =  zk.getData("/appconifg/redisconfig/redis.properties",true,null);
                      datastr =  new String(data);
                      System.out.println(datastr);*/

                    System.out.println("数据已经更新了...");
                }
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    byte [] data  = new byte[0];
                    try {
                        data = zk.getData("/appconifg/redisconfig/redis.properties",true,null);
                        List<String> path = zk.getChildren("/dubbo",true);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //datastr =  new String(data);
                    System.out.println( new String(data));
                    System.out.println("---------------------");
                    try {
                        Thread.sleep(1000*30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        //Thread.sleep(Long.MAX_VALUE);
        System.out.println("main执行完毕");
    }
}
