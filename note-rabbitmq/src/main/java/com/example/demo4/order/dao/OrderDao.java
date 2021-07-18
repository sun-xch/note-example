package com.example.demo4.order.dao;

import com.example.demo4.entity.RabbitmqOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    int insertOrder(RabbitmqOrder rabbitmqOrder);
}
