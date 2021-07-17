package com.example.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 简单模式 - 生产者
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
            //4.通过创建交换机,声明队列,绑定关系,路由key,返送消息,和接受消息
            String queueName = "queue1";
            /**
             * 声明名队列
             * @params1 队列的名称
             * @params2 是否要持久化durable=false 持久化指消息是否存盘，false:非持久化，true:持久化   非持久化队列消息会存盘吗？ 会存盘，但是随着重启服务会丢失
             * @params3 排他性,是否是独占队列
             * @params4 是否自动删除，队列中最后一条消息消费完毕后是否把队列自动删除
             * @params5 携带附属参数
             */
            channel.queueDeclare(queueName,false,false,false,null);
            //5.准备消息内容
            String message = "Hello world!";
            //6.发送消息个队列queue
            //参数3表示消息是否持久化 这里先过
            //虽然没有指定交换机，但是一定会存在一个默认的交换机
            channel.basicPublish("",queueName,null,message.getBytes());
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
