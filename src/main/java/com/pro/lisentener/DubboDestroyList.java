package com.pro.lisentener;

import org.apache.dubbo.registry.dubbo.DubboRegistryFactory;
import org.apache.dubbo.rpc.protocol.dubbo.DubboProtocol;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DubboDestroyList implements ServletContextListener {
   /* @Override
    public void contextInitialized(ServletContextEvent sce) {

    }*/

    /*Tomcat停止的时候，释放Dubbo所有占用的资源*/
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DubboRegistryFactory.destroyAll();

        DubboProtocol.getDubboProtocol().destroy();
    }
}
