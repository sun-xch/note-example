package com.example.demo1.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FanoutProductService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 模拟下单
     * @param userId
     * @param productId
     * @param num
     */
    public void makeOrder(String userId, String productId, int num){

        String orderId = UUID.randomUUID().toString();

        //通过mq分发消息
        String exchangeName = "fanout_order_exchange";
        String routingKey = "";

        /**
         * @params1:交换器
         * @params2:路由key/队列名称
         * @Params3:消息内容
         */
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }


}
