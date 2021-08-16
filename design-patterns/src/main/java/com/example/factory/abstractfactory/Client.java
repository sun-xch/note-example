package com.example.factory.abstractfactory;

public class Client {
    public static void main(String[] args) {

        System.out.println("===========小米系列=========");
        XiaomiFactory xiaomiFactory = new XiaomiFactory();

        IPhoneProduct xiaomiPhone = xiaomiFactory.phoneProduct();
        xiaomiPhone.callUp();
        xiaomiPhone.sendSMS();
        IRouterProduct xiaomiRouter = xiaomiFactory.routerProduct();
        xiaomiRouter.openWifi();

        System.out.println("===========华为系列=========");
        HuaWeiFactory huaWeiFactory = new HuaWeiFactory();

        IPhoneProduct huaweiPhone = huaWeiFactory.phoneProduct();
        huaweiPhone.callUp();
        huaweiPhone.sendSMS();
        IRouterProduct huaweiRouter = huaWeiFactory.routerProduct();
        huaweiRouter.openWifi();

    }
}
