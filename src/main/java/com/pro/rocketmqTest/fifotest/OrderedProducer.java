package com.pro.rocketmqTest.fifotest;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.List;
/**
 * 描述:下面的示例演示了全局和分区排序消息的发送/接收
 * RocketMQ使用FIFO顺序提供有序消息。
 * 发送消息
 * @param null
 * return
 * Author Dai Wu
 * Date 2020/5/23 12:28
 *
 * **/
public class OrderedProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("example_group_name_daiwu");
        producer.setNamesrvAddr("192.168.119.134:9876");
        producer.setSendMsgTimeout(1000*60*60);
        //producer.setDefaultTopicQueueNums();
        //Launch the instance.
        producer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 1; i++) {
            int orderId = i % 10;
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest_daiwu2", tags[i % tags.length], "KEY" + i,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    System.out.println(new String(msg.getBody()));
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    return mqs.get(index);
                }
            }, orderId);

            System.out.printf("%s%n", sendResult);
        }
        //server shutdown
        producer.shutdown();
    }
}
