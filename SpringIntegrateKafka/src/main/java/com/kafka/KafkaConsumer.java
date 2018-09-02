package com.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author apktool
 * @date 2018-08-31 14:46
 */

public class KafkaConsumer implements MessageListener<String, String> {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    public void onMessage(ConsumerRecord<String, String> record) {
        logger.info("=============kafkaConsumer开始消费=============");
        logger.info("-------------topic:" + record.topic());
        logger.info("-------------key:" + record.key());
        logger.info("-------------value:" + record.value());
        logger.info("-------------offset:" + record.offset());
        logger.info("-------------partition:" + record.partition());
        logger.info("=============kafkaConsumer消费结束=============");
        System.out.println("**************消费成功******************");
    }
}
