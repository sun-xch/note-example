package com.example.factory.abstractfactory;

public class XiaomiFactory implements IProductFactory{
    @Override
    public IPhoneProduct phoneProduct() {
        return new XiaomiPhone();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new XiaomiRouter();
    }
}
