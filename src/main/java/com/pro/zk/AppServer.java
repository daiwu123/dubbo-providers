package com.pro.zk;

import org.apache.log4j.PropertyConfigurator;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.File;

public class AppServer {
    private static String groupNode = "sgroup";
    private String subNode = "sub";
    ZooKeeper zk;

    private void updateConfig() throws KeeperException, InterruptedException {
//注意getdata 参数 boolean watch,这里一定要是true，说明每次都要监控节点数据变化的
        System.out.println(zk.hashCode()+": current config is:"+new String(zk.getData("/" + groupNode, true, null)));
    }

    public void connectZookeeper(String address) throws Exception {
//zk集群服务地址
        zk = new ZooKeeper("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", 300*1000, new Watcher() {
            public void process(WatchedEvent event) {
                if( event.getType().equals(Event.EventType.NodeDataChanged) ){
                    System.out.println("config is changed");
                    try {
                        updateConfig();
                    } catch (KeeperException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
// 在"/sgroup"下创建子节点
// 子节点的类型设置为EPHEMERAL_SEQUENTIAL, 表明这是一个临时节点, 且在子节点的名称后面加上一串数字后缀
// 将server的地址数据关联到新创建的子节点上
        Stat s = zk.exists("/" + groupNode, false);
        if( s == null  ){
            zk.create("/" + groupNode, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            zk.setData("/" + groupNode, "origin config".getBytes(), -1);
        }
        String createdPath = zk.create("/" + groupNode + "/" + subNode,address.getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        zk.getData("/" + groupNode, true,null);
        System.out.println("create: " + createdPath);
    }


    /***
     * 模拟3个app使用共同配置文件。随后让一个线程去修改配置5次。3个app每次都会收到配置改变的通知。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//初始化log4j
      /*  File f = new File( "D:/log");
        if( !f.exists() ){
            f.mkdir();
            new File("D:/log"+ File.separator+"land.log");
        }
        PropertyConfigurator.configureAndWatch("D:/conf" + File.separator + "log4j.properties");*/

       // String[] hosts = new String[] { "10.15.107.105", "10.15.107.42","10.15.107.43" };
        String[] hosts = new String[] { "10.15.107.105","10.15.107.42"};
        for (int i = 0; i < hosts.length; ++i) {
            final AppServer as = new AppServer();
            as.connectZookeeper(hosts[i]);
            if( i == 1){
                new Thread(){
                    public void run(){
                        try {
                            for( int i=0;i<1;++i ){
                                System.out.println("config begin change");
                                as.zk.setData("/" + groupNode, (as.zk.hashCode()+" change id:"+Math.random()).getBytes(), -1);
                                Thread.sleep(3 * 1000);
                            }
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
            Thread.sleep(1 * 1000);
        }
        Thread.sleep(Long.MAX_VALUE);
    }
}
