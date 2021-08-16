package com.example.factorymethod;

public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.go();
        Plane plane = new Plane();
        plane.go();

        Moveable c = new Car();
        c.go();
    }
}
