package com.example.goosestrategy01;

public abstract class Goose {

    public void quack(){
        System.out.println("鹅叫...");
    }

    public void swim(){
        System.out.println("鹅游泳");
    }

    public abstract void display();

}
