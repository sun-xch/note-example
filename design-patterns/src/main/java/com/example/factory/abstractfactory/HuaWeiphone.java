package com.example.factory.abstractfactory;

public class HuaWeiphone implements IPhoneProduct{

    @Override
    public void start() {
        System.out.println("华为开机");
    }

    @Override
    public void shutDown() {
        System.out.println("华为关机");
    }

    @Override
    public void callUp() {
        System.out.println("华为打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("华为发短信");
    }
}
