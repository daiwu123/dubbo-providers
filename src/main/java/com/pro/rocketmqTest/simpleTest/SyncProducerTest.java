package com.pro.rocketmqTest.simpleTest;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * 描述:同步发送消息
 * @param null
 * return
 * Author Dai Wu
 * Date 2020/5/17 22:31
 *
 * **/
public class SyncProducerTest {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("please_rename_unique_group_name_1");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.119.134:9876");
        //Launch the instance.

        producer.start();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {

            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest1" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
            //Thread.sleep(1000);
        }
        //Shut down once the producer instance is not longer in use.
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        producer.shutdown();
    }
}
