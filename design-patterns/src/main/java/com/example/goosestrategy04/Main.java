package com.example.goosestrategy04;

/**
 * 既可以复用 也可以扩展
 */
public class Main {

    public static void main(String[] args) {

        Swan swan = new Swan();
        swan.setFlyBehavior(new FlyWithWings());
        swan.getFlyBehavior().fly();

    }
}
