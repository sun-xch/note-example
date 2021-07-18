package com.example.demo4.order.dao;

import com.example.demo4.entity.RabbitmqOrderMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMessgaeDao {

    int insertOrderMessage(RabbitmqOrderMessage rabbitmqOrderMessage);

    int updateOrderMessage(RabbitmqOrderMessage rabbitmqOrderMessage);
}
