package com.pro.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.pro.pj.Money;
import com.pro.redis.JedisUtils;
import com.pro.service.impl.DBService;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionConsumer implements InitializingBean {
    @Autowired
    private DBService dbService;
    @Autowired
    private JedisUtils jedisUtils;
    public void consumerMessage() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transactionConsumer_daiwu");
        consumer.setNamesrvAddr("192.168.119.134:9876");
        consumer.setConsumeMessageBatchMaxSize(10);
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("topicTransaction_daiwu", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    try {
                        if( jedisUtils.isExistsKey(msg.getTransactionId())) {
                            System.out.println(msg + ",内容：" + new String(msg.getBody()));
                            String jsonBody = new String(msg.getBody());
                            Money money = JSONObject.parseObject(jsonBody, Money.class);
                            dbService.plusMoney(money, msg.getTransactionId());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;// 重试
                    }
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;// 成功
            }
        });
        consumer.start();

        System.out.println("transaction_Consumer Started.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        consumerMessage();
    }
}
