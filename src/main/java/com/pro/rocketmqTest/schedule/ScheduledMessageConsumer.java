package com.pro.rocketmqTest.schedule;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;

import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**预定的消息与正常的消息的不同之处在于，它们要等到指定的时间后才能传递。
 * 描述:启动使用者以等待传入的订阅消息
 * @param null
 * return
 * Author Dai Wu
 * Date 2020/5/23 16:06
 *
 * **/
public class ScheduledMessageConsumer {
    public static void main(String[] args) throws MQClientException {
        // Instantiate message consumer
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ExampleConsumer");
        consumer.setNamesrvAddr("192.168.119.134:9876");
        // Subscribe topics
        consumer.subscribe("TestTopic_scheduled", "*");
        // Register message listener
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messages, ConsumeConcurrentlyContext context) {
                for (MessageExt message : messages) {
                    // Print approximate delay time period

                    System.out.println(System.currentTimeMillis()+":"+ message.getBornTimestamp());
                    System.out.println("Receive message[msgId=" + message.getMsgId() + "] "
                            + (System.currentTimeMillis() - message.getStoreTimestamp()) + "ms later");
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // Launch consumer
        consumer.start();
    }
}
