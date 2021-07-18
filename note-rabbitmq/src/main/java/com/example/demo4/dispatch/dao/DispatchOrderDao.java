package com.example.demo4.dispatch.dao;

import com.example.demo4.entity.RabbitmqDispacherOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatchOrderDao {

    int insertDispatchOrder(RabbitmqDispacherOrder rabbitmqDispacherOrder);
}
