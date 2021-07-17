package com.example.demo2.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectEmailConsumerService {

    @RabbitListener(queues = {"email.direct.queue"})
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email--接受到订单信息是："+message);
    }

}
