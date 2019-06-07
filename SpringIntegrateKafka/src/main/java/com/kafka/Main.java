package com.kafka;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author apktool
 * @date 2018-08-31 10:40
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext producerContext = new ClassPathXmlApplicationContext("kafkaProducer.xml");
        KafkaProducer kp = (KafkaProducer) producerContext.getBean("kafkaProducer");
        kp.send();

        ApplicationContext consumerContext = new ClassPathXmlApplicationContext("kafkaConsumer.xml");
        consumerContext.getBean("messageListenerContainer");
    }
}
