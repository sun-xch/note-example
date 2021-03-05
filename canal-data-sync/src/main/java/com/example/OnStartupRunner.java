package com.example;

import com.example.cancal.CanalInitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component//被spring容器管理
@Order(1)//如果多个自定义ApplicationRunner，用来标明执行顺序
public class OnStartupRunner implements ApplicationRunner {

    @Autowired
    private CanalInitHandler canalInitHandler;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        canalInitHandler.initCanalStart();
    }
}
