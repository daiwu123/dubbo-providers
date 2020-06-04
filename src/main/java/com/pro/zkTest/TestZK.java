package com.pro.zkTest;


import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class TestZK {

    public static String[] zkServerIp = {"127.0.0.1:2181","127.0.0.1:2182","127.0.0.1:2183"};
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZooKeeper zkclient  = new ZooKeeper(StringUtils.join(zkServerIp,","),3000,new ZKWatcher());
        ZKClientConfig clientConfig = zkclient.getClientConfig();
        Stat exists = zkclient.exists("/", false);
        if(exists!=null){
            byte[] data = zkclient.getData("/", new ZKWatcher(), new Stat());
            Thread.sleep(2000);
            System.out.println(new String(data));
        }else{
            System.out.println("null");
        }

    }
}
