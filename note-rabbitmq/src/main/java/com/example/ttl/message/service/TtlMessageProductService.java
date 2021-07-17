package com.example.ttl.message.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TtlMessageProductService {

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
        String exchangeName = "ttl_message_direct_exchange";

        //给消息设置过期时间
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //设置过期时间
                message.getMessageProperties().setExpiration("5000");
                //还可以设置编码
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };

        /**
         * @params1:交换器
         * @params2:路由key/队列名称
         * @Params3:消息内容
         */
        rabbitTemplate.convertAndSend(exchangeName,"ttlMessage",orderId,messagePostProcessor);

    }


}
