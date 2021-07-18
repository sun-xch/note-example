package com.example.demo4.order.service;

import com.example.demo4.entity.RabbitmqOrder;
import com.example.demo4.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Transactional
    public void insertOrder(String id) throws Exception {

        RabbitmqOrder rabbitmqOrder = new RabbitmqOrder();
        rabbitmqOrder.setOrderId(id);
        rabbitmqOrder.setUserId(id);
        rabbitmqOrder.setOrderContent(id);
        rabbitmqOrder.setCreateTime(new Date());

        orderDao.insertOrder(rabbitmqOrder);

        String result = dispatchHttpApi(id);

        if(!"success".equals(result)){
            throw new Exception("订单创建失败！");
        }

    }

    private String dispatchHttpApi(String id){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        // 连接超时时间
        factory.setConnectTimeout(10000);
        // 处理超时时间
        factory.setReadTimeout(20000);
        String url = "http://localhost:8086/dispatch/order?id="+id;
        RestTemplate restTemplate = new RestTemplate(factory);
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }


}
