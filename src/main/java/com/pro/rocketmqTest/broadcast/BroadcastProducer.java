package com.pro.rocketmqTest.broadcast;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 描述: 广播
 * 广播正在向主题的所有订户发送一条消息。如果希望所有订阅者都收到有关主题的消息，那么广播是一个不错的选择
 * @param null 
 * return
 * Author Dai Wu
 * Date 2020/5/23 15:19
 * 
 * **/
public class BroadcastProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
        producer.setNamesrvAddr("192.168.119.134:9876");
        producer.start();

        for (int i = 0; i < 1; i++){
            Message msg = new Message("TopicTest_tt1",
                    "TagA",
                    "OrderID188",
                    "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}
