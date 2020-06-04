package com.pro.rocketmqTest.schedule;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.store.CommitLog;
import org.apache.rocketmq.store.config.MessageStoreConfig;


public class ScheduledMessageProducer {
    public static void main(String[] args) throws Exception {
        // Instantiate a producer to send scheduled messages
        DefaultMQProducer producer = new DefaultMQProducer("ExampleProducerGroup");
        producer.setNamesrvAddr("192.168.119.134:9876");
        // Launch producer
        producer.start();
        int totalMessagesToSend = 1;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic_scheduled", ("Hello scheduled message " + i).getBytes());
            // This message will be delivered to consumer 10 seconds later.
           message.setDelayTimeLevel(6);
            // Send the message
            producer.send(message);
            System.out.println(System.currentTimeMillis());
        }

        // Shutdown producer after use.
        producer.shutdown();
    }
}
