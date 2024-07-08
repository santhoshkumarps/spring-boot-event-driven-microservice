package com.javalearn.orderservice.controller;

import com.javalearn.baseDomains.dto.Order;
import com.javalearn.baseDomains.dto.OrderEvent;
import com.javalearn.orderservice.kafka.OrderKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderKafkaProducer orderKafkaProducer;


    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent event = new OrderEvent();
        event.setMessage("Order status is pending");
        event.setStatus("PENDING");
        event.setOrder(order);

        orderKafkaProducer.sendMessage(event);

        return "Order placed successfully";

    }

}
