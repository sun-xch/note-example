package com.example.factorymethod;

/**
 * 工厂方法
 */
public class CarFactory {

    public Moveable create(){
        //before processing
        return new Car();
    }

}
