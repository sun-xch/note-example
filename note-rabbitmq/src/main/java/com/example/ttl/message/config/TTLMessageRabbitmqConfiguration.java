package com.example.ttl.message.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 给队列设置过期时间
 */
@Configuration
public class TTLMessageRabbitmqConfiguration {

    @Bean
    public DirectExchange ttlMessageDirectExchange(){
        /**
         * @params1:交换机名称
         * @params2:是否持久化
         * @params3:是否自动删除
         */
        return new DirectExchange("ttl_message_direct_exchange",true,false);
    }

    @Bean
    public Queue directTtlMessageQueue(){


        return new Queue("ttl.message.direct.queue",true);
    }

    //3.完成绑定关系
    @Bean
    public Binding directTtlMessageBinding(){
        return BindingBuilder.bind(directTtlMessageQueue()).to(ttlMessageDirectExchange()).with("ttlMessage");
    }

}
