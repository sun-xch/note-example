package com.example.factory.simple;

public class Consumer {

    public static void main(String[] args) {

        Car wuLing = new WuLing();

        Car tesla = new Tesla();

        wuLing.name();

        tesla.name();

        //使用工厂创建
        Car car1 = CarFactory.getCar("五菱");
        Car car2 = CarFactory.getCar("特斯拉");

        car1.name();
        car2.name();
    }

    //结构复杂度：simple
    //代码复杂度：simple
    //编程复杂度：simple
    //管理上的复杂度：simple

    //根据设计原则：工厂方法模式
    //根据实际业务：简单工厂模式
}
