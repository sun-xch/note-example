package com.example.factory.abstractfactory;

public class HuaWeiRouter implements IRouterProduct{
    @Override
    public void openWifi() {
        System.out.println("华为WIFI");
    }

    @Override
    public void setting() {
        System.out.println("华为路由设置");
    }
}
