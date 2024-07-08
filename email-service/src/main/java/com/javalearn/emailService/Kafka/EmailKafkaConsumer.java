package com.javalearn.emailService.Kafka;

import com.javalearn.baseDomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailKafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(EmailKafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void emailConsume(OrderEvent event){
        log.info(String.format("order details %s is sent to your email",event.toString()));
    }

}
