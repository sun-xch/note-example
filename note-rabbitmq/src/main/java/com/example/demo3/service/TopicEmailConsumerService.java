package com.example.demo3.service;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class TopicEmailConsumerService {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "email.topic.queue",durable = "false",autoDelete = "false"),
            exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
            key = "#.email.#"
    ))
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email--接受到订单信息是："+message);
    }

}
