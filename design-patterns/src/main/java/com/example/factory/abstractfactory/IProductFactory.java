package com.example.factory.abstractfactory;

/**
 * 抽象产品工厂
 */
public interface IProductFactory {

    IPhoneProduct phoneProduct();

    IRouterProduct routerProduct();
}
