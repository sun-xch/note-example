package com.example.goosestrategy02;

public class WildGoose extends Goose implements Flyable{

    @Override
    public void display() {
        System.out.println("我是一只大雁");
    }

    @Override
    public void fly() {
        System.out.println("飞....");
    }
}
