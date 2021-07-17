package com.example.rabbitmq.all;

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

            //4.准备消息内容
            String message = "Hello world!";

            //5.准备交换机
            String exchangeName = "direct_message_exchange";
            //定义路由key
            String routeKey = "msg";
            //指定交换机类型
            String type = "direct";
            // 交换机声明
            /**
             * params1:交换机名称
             * params2:交换机类型
             * params3:是否持久化
             */
            channel.exchangeDeclare(exchangeName,type,true);

            //6.声明队列
            channel.queueDeclare("queue4",true,false,false,null);


            //7.绑定关系
            channel.queueBind("queue4",exchangeName,routeKey);

            //8.发送消息给队列queue
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
            //9.关闭通道
            if(channel != null && channel.isOpen()){
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //10.关闭连接
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
