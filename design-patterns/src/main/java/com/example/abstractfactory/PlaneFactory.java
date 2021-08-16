package com.example.abstractfactory;

public class PlaneFactory extends AbstractFactory{
    @Override
    Food createFood() {
        return new PlaneMeal();
    }

    @Override
    Vehicle createVehicle() {
        return new Plane();
    }
}
