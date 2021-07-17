package com.example.demo1.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * springboot fanout模式(发布 订阅模式)
 */
@Configuration
public class FanoutRabbitmqConfiguration {

    //1.声明fanout模式交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        /**
         * @params1:交换机名称
         * @params2:是否持久化
         * @params3:是否自动删除
         */
        return new FanoutExchange("fanout_order_exchange",true,false);
    }

    //2.声明sms.fanout.queue,email.fanout.queue,wechat.fanout.queue
    @Bean
    public Queue fanoutSmsQueue(){
        return new Queue("sms.fanout.queue",true);
    }

    @Bean
    public Queue fanoutEmailQueue(){
        return new Queue("email.fanout.queue",true);
    }

    @Bean
    public Queue fanoutWechatQueue(){
        return new Queue("wechat.fanout.queue",true);
    }

    //3.完成绑定关系
    @Bean
    public Binding fanoutSmsBinding(){
        return BindingBuilder.bind(fanoutSmsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutEmailBinding(){
        return BindingBuilder.bind(fanoutEmailQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutWechatBinding(){
        return BindingBuilder.bind(fanoutWechatQueue()).to(fanoutExchange());
    }

}
