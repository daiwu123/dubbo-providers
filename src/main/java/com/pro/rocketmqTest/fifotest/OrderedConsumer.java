package com.pro.rocketmqTest.fifotest;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 描述:订阅消息
 * @param null
 * return
 * Author Dai Wu
 * Date 2020/5/23 12:29
 *
 * **/
public class OrderedConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_group_name_daiwu");
        consumer.setNamesrvAddr("192.168.119.134:9876");
       // consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //consumer.subscribe("TopicTest_daiwu2", "TagA || TagC || TagD");
        consumer.subscribe("TopicTest_daiwu2","*");
        consumer.registerMessageListener(new MessageListenerOrderly() {

            AtomicLong consumeTimes = new AtomicLong(0);
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                       ConsumeOrderlyContext context) {
                context.setAutoCommit(true);
               // context.setSuspendCurrentQueueTimeMillis(3000);
                for (int i = 0; i < msgs.size(); i++) {
                    MessageExt messageExt=  msgs.get(i);
                    System.out.println(Thread.currentThread().getName()+"Receive New Messages: "+new String(messageExt.getBody()));
                   // System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + new String(messageExt.getBody()) + "%n");
                }
               // System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                this.consumeTimes.incrementAndGet();
                System.out.println(this.consumeTimes.get());

                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
               /* if ((this.consumeTimes.get() % 2) == 0) {
                    return ConsumeOrderlyStatus.SUCCESS;
                } else if ((this.consumeTimes.get() % 3) == 0) {
                    return ConsumeOrderlyStatus.ROLLBACK;
                } else if ((this.consumeTimes.get() % 4) == 0) {
                    return ConsumeOrderlyStatus.COMMIT;
                } else if ((this.consumeTimes.get() % 5) == 0) {
                    context.setSuspendCurrentQueueTimeMillis(3000);
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }*/
               // return ConsumeOrderlyStatus.SUCCESS;

            }
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}
