package com.pro.rocketmqTest.trasation;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException {
        // 构造事务消息的生产者
        TransactionMQProducer producer = new TransactionMQProducer("transactionProducer");
        producer.setNamesrvAddr("192.168.119.134:9876");
        LinkedBlockingDeque<Runnable> linkQueue = new LinkedBlockingDeque<>(2000);
        ExecutorService executorService =  new ThreadPoolExecutor(2,20,1000*60,TimeUnit.MILLISECONDS,linkQueue);
        producer.setExecutorService(executorService);

        // 设置事务决断处理类
        producer.setTransactionListener(new TransactionCheckListenerImpl());
        producer.start();
        for (int i = 1; i <= 2; i++) {
            try {
                String tags = "transaction" + i;
                String keys = "KEY" + i;
                byte[] body = ("Hello RocketMQ " + i).getBytes();
                Message msg = new Message("topicTransaction", tags, keys, body);
                SendResult sendResult = producer.sendMessageInTransaction(msg, null);
                System.out.println(sendResult);
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
        //producer.shutdown();
        // 事务回查最小并发数
        // producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并发数
        // producer.setCheckThreadPoolMaxSize(20);
        //对列数
        // producer.setCheckRequestHoldMax(2000);
    }


}
