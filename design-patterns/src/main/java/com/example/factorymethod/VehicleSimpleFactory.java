package com.example.factorymethod;

/**
 * 简单工厂 扩展性不好
 */
public class VehicleSimpleFactory {

    public Car createCar(){
        //before processing
        return new Car();
    }

    public Plane createPlan(){
        return new Plane();
    }
}
