package com.pro.zkConfig;

import com.m.config.RedisConfig;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ZKWatcher implements Watcher {
    @Autowired
    private ZooKeeper zk;
    @Autowired
    private RedisConfig redisConfig;
    @Override
    public void process(WatchedEvent event) {
        if(event.getType().equals(Event.EventType.NodeDataChanged)){
            //数据发生改变
            /*try {
                List<String> childs = zk.getChildren("/config/gloab",true);
                for(String option:childs){
                    if(option)
                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }else if(event.getType().equals(Event.EventType.NodeChildrenChanged)){
            //子节点发生改变
        }else if(event.getType().equals(Event.EventType.NodeDeleted)){
            //只要有节点被删除就要触发
        }else if(event.getType().equals(Event.EventType.NodeCreated)){
            //节点创建时触发
        }else if(event.getType().equals(Event.EventType.DataWatchRemoved)){
            //???
        }else if(event.getType().equals(Event.EventType.ChildWatchRemoved)){
            //????
        }
    }
}
