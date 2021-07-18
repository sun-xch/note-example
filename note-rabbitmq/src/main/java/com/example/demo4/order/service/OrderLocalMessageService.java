package com.example.demo4.order.service;

import com.example.demo4.entity.RabbitmqOrder;
import com.example.demo4.entity.RabbitmqOrderMessage;
import com.example.demo4.order.dao.OrderDao;
import com.example.demo4.order.dao.OrderMessgaeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderLocalMessageService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderMessgaeDao orderMessgaeDao;

    @Autowired
    private OrderMqService orderMqService;

    public void insertOrder(String id) throws Exception {

        RabbitmqOrder rabbitmqOrder = new RabbitmqOrder();
        rabbitmqOrder.setOrderId(id);
        rabbitmqOrder.setUserId(id);
        rabbitmqOrder.setOrderContent(id);
        rabbitmqOrder.setCreateTime(new Date());

        orderDao.insertOrder(rabbitmqOrder);
        // 消息冗余
        insertOrderMessage(id);

        orderMqService.sendMessage(id);

    }

    public void insertOrderMessage(String id){
        RabbitmqOrderMessage rabbitmqOrderMessage = new RabbitmqOrderMessage();
        rabbitmqOrderMessage.setOrderId(id);
        rabbitmqOrderMessage.setStatus("0");
        rabbitmqOrderMessage.setOrderContent(id);
        rabbitmqOrderMessage.setUniqueId(id);

        orderMessgaeDao.insertOrderMessage(rabbitmqOrderMessage);
    }
}
