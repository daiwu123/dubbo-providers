package com.pro.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZKAppServer {
    private static String groupNode = "sgroup";
    private String subNode = "sub";
    ZooKeeper zk;
    private void updateConfig() throws KeeperException, InterruptedException {
        System.out.println(zk.hashCode()+"curent config is"+new String(zk.getData("/"+groupNode,true,null)));
      //  zk.getData("/"+groupNode,true,null);
    }

    public void connectionZookeeper(String address) throws IOException, KeeperException, InterruptedException {
        zk = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 1000 * 30, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(event.getType().equals(Event.EventType.NodeDataChanged)){
                    System.out.println("config is changed");
                }
                try {
                    updateConfig();
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Stat stat =  zk.exists("/"+groupNode,false);
        if(stat ==null){
            zk.create("/"+groupNode,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zk.setData("/"+groupNode,"origin Config".getBytes(),-1);
        }
        String createPath =   zk.create("/"+groupNode+"/"+subNode,address.getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Create:"+createPath);
    }

    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        String [] ports = {"2181","2182","2183"};
        for (int i = 0; i < ports.length; i++) {
            ZKAppServer apserver = new ZKAppServer();
            apserver.connectionZookeeper(ports[i]);
            if(i==2){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < 1; j++) {
                            System.out.println("config begin change");
                            try {
                                apserver.zk.setData("/"+groupNode,(apserver.zk.hashCode()+" change id:"+Math.random()).getBytes(),-1);
                                Thread.sleep(3000);
                            } catch (KeeperException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        }
    }
}

