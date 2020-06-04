package com.pro.rocketmqTest.simpleTest;


import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.BrokerConfig;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * 描述:异步发送消息
 * 异步传输通常用于对时间敏感的业务场景中。
 * @param null 
 * return  
 * Author Dai Wu
 * Date 2020/5/17 23:02
 * 
 * **/
public class AsyncProducerTest {
    public static void main(String[] args) throws MQClientException, IOException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // Specify name server addresses.
       // BrokerConfig
       // waitTimeMillsInSendQueue
        producer.setNamesrvAddr("192.168.119.134:9876");
        //Launch the instance.
        producer.start();
        int k = 100000;
        producer.setRetryTimesWhenSendAsyncFailed(0);
        producer.setSendMsgTimeout(1000*60);
        long startTime = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(k);
        for (int i = 0; i < k; i++) {
            final int index = i;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest2",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
         //   SendResult result = producer.send(msg);

         //   System.out.printf("????????"+result.getSendStatus().name());
            producer.send(msg,new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });

        }
        countDownLatch.await();
        System.out.println("已经发送完毕");
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}
