package com.javalearn.orderservice.kafka;

import com.javalearn.baseDomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(OrderKafkaProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderKafkaProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent event){
        log.info(String.format("Order event %s ",event.toString()));

        // Message event

        Message<OrderEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);

    }

}
