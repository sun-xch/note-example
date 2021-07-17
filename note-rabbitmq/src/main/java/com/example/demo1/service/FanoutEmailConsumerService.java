package com.example.demo1.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutEmailConsumerService {

    @RabbitListener(queues = {"email.fanout.queue"})
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("email--接受到订单信息是："+message);
    }

}
