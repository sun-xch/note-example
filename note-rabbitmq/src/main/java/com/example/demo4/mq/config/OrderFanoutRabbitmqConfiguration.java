package com.example.demo4.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * springboot fanout模式(发布 订阅模式)
 */
@Configuration
public class OrderFanoutRabbitmqConfiguration {

    //1.声明fanout模式交换机
    @Bean
    public FanoutExchange orderFanoutExchange(){
        /**
         * @params1:交换机名称
         * @params2:是否持久化
         * @params3:是否自动删除
         */
        return new FanoutExchange("demo4_order_fanout_exchange",true,false);
    }

    @Bean
    public Queue fanoutOrderQueue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange","dead_fanout_exchange");
        return new Queue("demo4.order.fanout.queue",true,false,false,args);
    }

    //3.完成绑定关系
    @Bean
    public Binding fanoutSmsBinding(){
        return BindingBuilder.bind(fanoutOrderQueue()).to(orderFanoutExchange());
    }

    @Bean
    public FanoutExchange deadFanoutExchange(){
        /**
         * @params1:交换机名称
         * @params2:是否持久化
         * @params3:是否自动删除
         */
        return new FanoutExchange("dead_fanout_exchange",true,false);
    }

    @Bean
    public Queue deadFanoutQueue(){
        return new Queue("dead.fanout.queue",true);
    }

    @Bean
    public Binding deadFanoutBinding(){
        return BindingBuilder.bind(deadFanoutQueue()).to(deadFanoutExchange());
    }

}
