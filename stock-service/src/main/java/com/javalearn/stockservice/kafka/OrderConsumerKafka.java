package com.javalearn.stockservice.kafka;

import com.javalearn.baseDomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumerKafka {

    private static final Logger log = LoggerFactory.getLogger(OrderConsumerKafka.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event){

        log.info(String.format("order event received in stock service %s",event.toString()));

        // Future: save order event in the database

    }


}
