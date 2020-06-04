package com.pro.zkTest;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ZKWatcher implements Watcher {
    @Override
    public void process(WatchedEvent event) {
        System.out.println("Test Zk。。。。。。");
        System.out.println("Test Zk-------->Path--->"+event.getPath());
        System.out.println("Test Zk-------->State--->"+event.getState().name());
        System.out.println("Test Zk-------->EventPath-->"+event.getWrapper().getPath());

    }
}
