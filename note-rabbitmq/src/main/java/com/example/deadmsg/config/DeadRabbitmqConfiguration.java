package com.example.deadmsg.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 给队列设置过期时间
 */
@Configuration
public class DeadRabbitmqConfiguration {

    @Bean
    public DirectExchange DeadDirectExchange(){
        /**
         * @params1:交换机名称
         * @params2:是否持久化
         * @params3:是否自动删除
         */
        return new DirectExchange("dead_direct_exchange",true,false);
    }

    @Bean
    public Queue deadDirectQueue(){
        return new Queue("dead.direct.queue",true);
    }

    //3.完成绑定关系
    @Bean
    public Binding deadDirectBinding(){
        return BindingBuilder.bind(deadDirectQueue()).to(DeadDirectExchange()).with("dead");
    }

}
