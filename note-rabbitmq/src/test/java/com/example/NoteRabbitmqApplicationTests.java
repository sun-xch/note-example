package com.example;

import com.example.demo1.service.FanoutProductService;
import com.example.demo2.service.DirectOrderProductService;
import com.example.demo3.service.TopicOrderProductService;
import com.example.demo4.order.service.OrderLocalMessageService;
import com.example.demo4.order.service.OrderService;
import com.example.ttl.message.service.TtlMessageProductService;
import com.example.ttl.queue.service.TtlQueueOrderProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoteRabbitmqApplicationTests {

    @Autowired
    private FanoutProductService fanoutProductService;

    @Autowired
    private DirectOrderProductService directOrderProductService;

    @Autowired
    private TopicOrderProductService topicOrderProductService;

    @Autowired
    private TtlQueueOrderProductService ttlQueueOrderProductService;

    @Autowired
    private TtlMessageProductService ttlMessageProductService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderLocalMessageService orderLocalMessageService;

    @Test
    void contextLoads() {
    }

    /**
     * fanout模式(订阅模式)
     */
    @Test
    public void fanouTest(){
        fanoutProductService.makeOrder("1","1",1);
    }

    /**
     * direct模式(路由模式)
     */
    @Test
    public void directTest(){
        directOrderProductService.makeOrder("1","1",1);
    }

    /**
     * topic模式(主题模式)
     */
    @Test
    public void topicTest(){
        topicOrderProductService.makeOrder("1","1",1);
    }

    /**
     * 设置队列过期时间
     */
    @Test
    public void ttlQueueTest(){
        ttlQueueOrderProductService.makeOrder("1","1",1);
    }

    /**
     *设置消息过期时间
     */
    @Test
    public void ttlMessageTest(){
        ttlMessageProductService.makeOrder("1","1",1);
    }

    @Test
    public void orderCreateTest() throws Exception {
        String id = "1";
        orderService.insertOrder(id);
    }

    @Test
    public void orderMessageTest() throws Exception {
        String id = "1";
        orderLocalMessageService.insertOrder(id);
    }
}
