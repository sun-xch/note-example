package com.example.rabbitmq.work.polling;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Work1 {

    public static void main(String[] args) {

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
            connection = connectionFactory.newConnection("消费者-work1");
            //3.通过连接获取通道Channel
            channel = connection.createChannel();
            //4.通过创建交换机,声明队列,绑定关系,路由key,返送消息,和接受消息
            channel.basicConsume("queue1", true, new DeliverCallback() {
                @Override
                public void handle(String consumerTag, Delivery message) throws IOException {
                    System.out.println("work1-收到的消息是：" + new String(message.getBody(), "UTF-8"));
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, new CancelCallback() {
                @Override
                public void handle(String consumerTag) throws IOException {
                    System.out.println("work1-接收消息失败......");
                }
            });
            System.out.println("work1-开始接收消息");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //7.关闭通道
            if(channel != null && channel.isOpen()){
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //8.关闭连接
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
