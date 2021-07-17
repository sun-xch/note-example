package com.example.demo3.service;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class TopicWechatConsumerService {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "wechat.topic.queue",durable = "false",autoDelete = "false"),
            exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
            key = "#.wechat.#"
    ))
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("wechat--接受到订单信息是："+message);
    }

}
