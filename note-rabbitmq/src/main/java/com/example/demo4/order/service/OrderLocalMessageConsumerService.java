package com.example.demo4.order.service;

import com.example.demo4.dispatch.service.DispatchOrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class OrderLocalMessageConsumerService {

    @Autowired
    private DispatchOrderService dispatchOrderService;

    /**
     * 解决消息重试的几种方案
     * 1.控制重发次数
     * 2.try-catch + 手动ack
     * 3.try-catch + 手动ack + 死信队列
     * @param ordermsg
     * @throws Exception
     */
    @RabbitListener(queues = {"demo4.order.fanout.queue"})
    @RabbitHandler
    public void messageConsumer(String ordermsg, Channel channel, CorrelationData correlationData, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
//        System.out.println("收到mq的消息是：" + ordermsg);
//
//        System.out.println(1/0);
//
//        dispatchOrderService.insertDispatchOrder(ordermsg);

        try {
            System.out.println("收到mq的消息是：" + ordermsg);

            System.out.println(1/0);

            dispatchOrderService.insertDispatchOrder(ordermsg);
            channel.basicAck(tag,false);
        }catch (Exception e){
            /**
             * @params1:消息的tag
             * @params2:是否多条处理
             * @params3:requeue 是否重发
             */
            channel.basicNack(tag,false,false);
        }

    }
}
