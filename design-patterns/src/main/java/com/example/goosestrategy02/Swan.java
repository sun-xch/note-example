package com.example.goosestrategy02;

public class Swan extends Goose implements Flyable{

    @Override
    public void display() {
        System.out.println("我是一只天鹅");
    }

    @Override
    public void fly() {
        System.out.println("飞....");
    }

}
