package com.example.demo2.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springboot Direct模式(路由模式)
 */
@Configuration
public class DirectRabbitmqConfiguration {

    //1.声明fanout模式交换机
    @Bean
    public DirectExchange directExchange(){
        /**
         * @params1:交换机名称
         * @params2:是否持久化
         * @params3:是否自动删除
         */
        return new DirectExchange("direct_order_exchange",true,false);
    }

    //2.声明sms.fanout.queue,email.fanout.queue,wechat.fanout.queue
    @Bean
    public Queue directSmsQueue(){
        return new Queue("sms.direct.queue",true);
    }

    @Bean
    public Queue directEmailQueue(){
        return new Queue("email.direct.queue",true);
    }

    @Bean
    public Queue directWechatQueue(){
        return new Queue("wechat.direct.queue",true);
    }

    //3.完成绑定关系
    @Bean
    public Binding directSmsBinding(){
        return BindingBuilder.bind(directSmsQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding directEmailBinding(){
        return BindingBuilder.bind(directEmailQueue()).to(directExchange()).with("email");
    }

    @Bean
    public Binding directWechatBinding(){
        return BindingBuilder.bind(directWechatQueue()).to(directExchange()).with("wechat");
    }

}
