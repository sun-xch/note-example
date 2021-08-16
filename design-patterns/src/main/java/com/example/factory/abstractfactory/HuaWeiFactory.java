package com.example.factory.abstractfactory;

public class HuaWeiFactory implements IProductFactory{
    @Override
    public IPhoneProduct phoneProduct() {
        return new HuaWeiphone();
    }

    @Override
    public IRouterProduct routerProduct() {
        return new HuaWeiRouter();
    }
}
