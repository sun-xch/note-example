package com.example.factory.abstractfactory;

public class XiaomiRouter implements IRouterProduct{
    @Override
    public void openWifi() {
        System.out.println("小米WIFI");
    }

    @Override
    public void setting() {
        System.out.println("小米路由设置");
    }
}
