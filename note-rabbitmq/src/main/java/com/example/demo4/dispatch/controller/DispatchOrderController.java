package com.example.demo4.dispatch.controller;

import com.example.demo4.dispatch.service.DispatchOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispatch")
public class DispatchOrderController {

    @Autowired
    private DispatchOrderService dispatchOrderService;

    @GetMapping("/order")
    public String insertDispatchOrder(@RequestParam String id) throws Exception {
        dispatchOrderService.insertDispatchOrder(id);
        return "success";
    }
}
