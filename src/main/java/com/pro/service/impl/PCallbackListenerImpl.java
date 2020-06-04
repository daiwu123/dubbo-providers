package com.pro.service.impl;

import com.s.service.CallbackFunction;
import com.s.service.CallbackService;
import org.apache.dubbo.config.annotation.Argument;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.annotation.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service(methods = {@Method(name="addListener",arguments = {@Argument(index = 1,callback = true)})},connections = 1,callbacks = 2,loadbalance = "roundrobin")
public class PCallbackListenerImpl implements CallbackService {


   // public final Map<String,CallbackFunction> functionMap = new HashMap<>();
   /* public PCallbackListenerImpl() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        for (Map.Entry<String, CallbackFunction> entry : functionMap.entrySet()) {
                            try {
                                entry.getValue().function(getChanged(entry.getKey()));
                            } catch (Throwable t) {
                                functionMap.remove(entry.getKey());
                            }
                        }
                        Thread.sleep(5000); // timely trigger change event
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
        t.setDaemon(true);
        t.start();
    }*/
   /* public PCallbackListenerImpl(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        for(Map.Entry<String,CallbackFunction> entry:functionMap.entrySet()){
                            try {
                                entry.getValue().function(jilu(entry.getKey()));
                                functionMap.remove(entry.getKey());
                            } catch (Exception e) {
                                e.printStackTrace();
                                functionMap.remove(entry.getKey());
                            }
                        }
                        System.out.println("<-----监听服务事件中------>"+functionMap.size());
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        *//*设置线程为守护线程，守护线程就是最低级别的线程，垃圾回收器就是典型的守护线程
        * 当没有垃圾的时候会自动离开，当存在垃圾的时候又会继续清理
        * 守护线程不建议处理业务逻辑，因为当没有非守护线程运行的时候，守护线程会立马关闭*//*
        t.setDaemon(true);
        t.start();
    }*/
   private String getChanged(String key) {
       return "Changed: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
   }

    public String  jilu(String key){
       String s = "Changed:提供者干活之后- "+key +" - - "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
       if(key.equalsIgnoreCase("2")){
           s = "Love You";
       }else if(key.equalsIgnoreCase("1")){
           s = "I dont Knoew";
       }
        return s;
    }

    @Override
    public void addListener(String key, CallbackFunction callbackFunction){
        //functionMap.put(key,callbackFunction);
        callbackFunction.function(jilu(key)); // send notification for change
        System.out.println("添加的Key");
        ///callbackFunction.function(jilu(key));
    }
}
