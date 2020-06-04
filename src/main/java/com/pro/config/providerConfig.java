package com.pro.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*@Configuration
@DubboComponentScan(basePackages = {"com.pro.service.impl","com.pro.dubboservice"})*/
public class providerConfig {

    /*配置生产者服务器名称*/
    @Bean
    public ApplicationConfig createAppConfig(){
        ApplicationConfig appConfig = new ApplicationConfig();
        appConfig.setName("providers-2");
        return appConfig;
    }
    /*配置注册中心，在哪里暴露接口地址*/
    @Bean
    public RegistryConfig createRegistryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setDynamic(true);
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }

    /*配置暴露服务的协议*/
    @Bean
    public ProtocolConfig createProtocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(21882);
        return protocolConfig;
    }
}
