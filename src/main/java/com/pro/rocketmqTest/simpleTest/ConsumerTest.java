package com.pro.rocketmqTest.simpleTest;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
/**
 * 描述: 消费消息
 * @param null
 * return
 * Author Dai Wu
 * Date 2020/5/17 23:02
 *
 * **/
public class ConsumerTest {
    public static void main(String[] args) throws MQClientException {
        final int[] i = {1};
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("please_rename_unique_group_name");

        // Specify name server addresses.
        consumer.setNamesrvAddr("192.168.119.134:9876");

        // Subscribe one more more topics to consume.
        consumer.subscribe("TopicTest1", "*");
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                System.out.println(i[0]++);

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }

        });

        //Launch the consumer instance.
        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
