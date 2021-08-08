package com.example.demo4.order.service;

import com.example.demo4.entity.RabbitmqOrderMessage;
import com.example.demo4.order.dao.OrderMessgaeDao;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class OrderMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderMessgaeDao orderMessgaeDao;

    /**
     * 可靠生产
     * @PostConstruct该注解被用来修饰一个非静态的void的方法，被@PostConstruct修饰的方法会在服务器加载servlet的时候运行，并且只会被服务器执行一次，
     * @PostConstruct在构造函数之后执行，init()方法之前执行
     */
    @PostConstruct
    public void regCallback(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * @param correlationData 对象内部只有一个 id 属性，用来表示当前消息的唯一性。
             * @param ack 消息投递到broker 的状态，true表示成功。
             * @param cause 表示投递失败的原因。
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("cause:"+cause);

                //如果ack为true代表消息已经收到
                String orderId = correlationData.getId();
                if(!ack){
                    System.out.println("mq队列应答失败，id是：" + orderId);
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RabbitmqOrderMessage rabbitmqOrderMessage = new RabbitmqOrderMessage();
                rabbitmqOrderMessage.setOrderId(orderId);
                rabbitmqOrderMessage.setStatus("1");
                orderMessgaeDao.updateOrderMessage(rabbitmqOrderMessage);
                System.out.println("本地状态修改成功，消息出成功投递到消息队列中");
            }
        });
    }

    public void sendMessage(String id){
        rabbitTemplate.convertAndSend("demo4_order_fanout_exchange","", id,new CorrelationData(id));
    }


}
