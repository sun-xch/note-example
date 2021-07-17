package com.example.ttl.queue.config;

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
public class TTLQueueRabbitmqConfiguration {

    @Bean
    public DirectExchange ttlQueueDirectExchange(){
        /**
         * @params1:交换机名称
         * @params2:是否持久化
         * @params3:是否自动删除
         */
        return new DirectExchange("ttl_queue_direct_exchange",true,false);
    }

    //2.声明队列并设置过期时间
    @Bean
    public Queue directTtlQueueQueue(){

        Map<String,Object> args = new HashMap<>();
        //过期时间为5秒
        args.put("x-message-ttl",5000);
        //队列最大长度
        //args.put("x-max-length",5);
        // 设置死信交换机
        args.put("x-dead-letter-exchange","dead_direct_exchange");
        args.put("x-dead-letter-routing-key","dead");

        return new Queue("ttl.queue.direct.queue",true,false,false,args);
    }

    //3.完成绑定关系
    @Bean
    public Binding directTtlQueueBinding(){
        return BindingBuilder.bind(directTtlQueueQueue()).to(ttlQueueDirectExchange()).with("ttlQueue");
    }

}
