package com.example.rabbitmq.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 订阅模式 - 消费者
 */
public class Consumer {

    private static Runnable runnable = new Runnable() {
        @Override
        public void run() {

            //1.创建连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("root");
            connectionFactory.setPassword("root");
            connectionFactory.setVirtualHost("/");//消息发送到根节点上

            //获取线程名 线程名 = 队列名
            String queueName = Thread.currentThread().getName();

            Connection connection = null;
            Channel channel = null;
            try {
                //2.创建连接Connection
                connection = connectionFactory.newConnection("生产者");
                //3.通过连接获取通道Channel
                channel = connection.createChannel();
                //4.通过创建交换机,声明队列,绑定关系,路由key,返送消息,和接受消息
                channel.basicConsume(queueName, true, new DeliverCallback() {
                    @Override
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println(queueName + "收到的消息是：" + new String(message.getBody(), "UTF-8"));
                    }
                }, new CancelCallback() {
                    @Override
                    public void handle(String consumerTag) throws IOException {
                        System.out.println(queueName + "接收消息失败......");
                    }
                });
                System.out.println(queueName + "开始接收消息");
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
    };

    public static void main(String[] args) {
        new Thread(runnable,"queue1").start();
        new Thread(runnable,"queue2").start();
        new Thread(runnable,"queue3").start();
    }

}
