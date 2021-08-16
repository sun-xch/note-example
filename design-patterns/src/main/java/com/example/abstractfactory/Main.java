package com.example.abstractfactory;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.go();
        Bread bread = new Bread();
        bread.printName();

        AbstractFactory factory = new CarFactory();
        Vehicle vehicle = factory.createVehicle();
        vehicle.go();

        Food food = factory.createFood();
        food.printName();
    }
}
