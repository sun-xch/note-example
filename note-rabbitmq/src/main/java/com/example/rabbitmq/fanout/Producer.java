package com.example.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 订阅模式 - 生产者
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

            //4.准备消息内容
            String message = "Hello world!";

            //5.准备交换机
            String exchangeName = "fanout-exchange";
            //定义路由key
            String routeKey = "";
            //指定交换机类型
            String type = "fanout";

            //6.发送消息给队列queue
            /**
             * @params1:交换机exchange
             * @params2:队列名称/routingKey
             * @params3:属性配置
             * @params4:发送消息内容
             */
            channel.basicPublish(exchangeName,routeKey,null,message.getBytes());
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
