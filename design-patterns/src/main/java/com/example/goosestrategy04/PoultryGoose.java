package com.example.goosestrategy04;

import com.example.goosestrategy03.Goose;

public class PoultryGoose extends Goose {

    private FlyBehavior flyBehavior;

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    @Override
    public void display() {
        System.out.println("我是一只家鹅");
    }

}
