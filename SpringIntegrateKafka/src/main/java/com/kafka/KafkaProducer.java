package com.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author apktool
 * @date 2018-08-31 10:53
 */

@Component("kafkaProducer")
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    String appConfigPath = rootPath + "application.properties";

    Properties props = new Properties();

    public KafkaProducer() throws IOException {
        props.load(new FileInputStream(appConfigPath));
    }

    @Autowired
    private KafkaTemplate kafkaTemplate;


    public void send() {
        String topic = props.getProperty("spring.kafka.topic");
        String key = "key";
        logger.info("=============kafkaProducer开始发送=============");
        for (int i = 0; i < 5; i++) {
            ListenableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, key, key + String.valueOf(i));
            logger.info(key + String.valueOf(i) + " 已经被成功发送");
        }
        logger.info("=============kafkaProducer发送完毕=============");
    }
}
