package com.example.demo4.dispatch.service;

import com.example.demo4.dispatch.dao.DispatchOrderDao;
import com.example.demo4.entity.RabbitmqDispacherOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DispatchOrderService {

    @Autowired
    private DispatchOrderDao dispatchOrderDao;

    public void insertDispatchOrder(String id) throws Exception {

        //Thread.sleep(30000L);

        RabbitmqDispacherOrder rabbitmqDispacherOrder = new RabbitmqDispacherOrder();
        rabbitmqDispacherOrder.setDispatchId(id);
        rabbitmqDispacherOrder.setOrderId(id);
        rabbitmqDispacherOrder.setUserId(id);
        rabbitmqDispacherOrder.setStatus(id);
        rabbitmqDispacherOrder.setOrderContent(id);
        rabbitmqDispacherOrder.setCreateTime(new Date());
        int i = dispatchOrderDao.insertDispatchOrder(rabbitmqDispacherOrder);

        if(i != 1){
            throw new Exception("运单创建失败");
        }
    }
}
