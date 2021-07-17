package com.example.demo1.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutWechatConsumerService {

    @RabbitListener(queues = {"wechat.fanout.queue"})
    @RabbitHandler
    public void receiveMessage(String message){
        System.out.println("wechat--接受到订单信息是："+message);
    }

}
