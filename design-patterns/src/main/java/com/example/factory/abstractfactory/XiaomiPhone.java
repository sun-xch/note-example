package com.example.factory.abstractfactory;

public class XiaomiPhone implements IPhoneProduct{
    @Override
    public void start() {
        System.out.println("小米开机");
    }

    @Override
    public void shutDown() {
        System.out.println("小米关机");
    }

    @Override
    public void callUp() {
        System.out.println("小米打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("小米发短信");
    }
}
