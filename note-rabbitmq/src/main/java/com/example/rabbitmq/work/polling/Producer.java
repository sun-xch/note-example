package com.example.rabbitmq.work.polling;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 路由模式 - 生产者
 */
public class Producer {

    public static void main(String[] args) {
        //rabbitmq遵循的是基于tcp/ip协议基础之上的amqp协议

        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        connectionFactory.setVirtualHost("/");//消息发送到根节点上

        Connection connection = null;
        Channel channel = null;
        try {
            //2.创建连接Connection
            connection = connectionFactory.newConnection("生产者");
            //3.通过连接获取通道Channel
            channel = connection.createChannel();

            for (int i = 0; i <= 20 ; i++) {
                String message = "hello world:" + i;
                //4.发送消息给队列queue
                channel.basicPublish("","queue1",null,message.getBytes());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭通道
            if(channel != null && channel.isOpen()){
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //6.关闭连接
            if(connection != null && connection.isOpen()){
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
