package com.example.goosestrategy04;

public class Swan extends Goose {

    private FlyBehavior flyBehavior;

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    @Override
    public void display() {
        System.out.println("我是一只天鹅");
    }
}
