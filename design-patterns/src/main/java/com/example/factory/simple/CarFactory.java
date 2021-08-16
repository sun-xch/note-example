package com.example.factory.simple;

/**
 * 静态工厂模式 / 简单工厂模式
 * 增加新品，需要修改代码
 */
public class CarFactory {

    // 方法一: 开闭原则 在增加新品时需要修改代码
    public static Car getCar(String car){
        if("五菱".equals(car)){
            // new 之前的细节
            return new WuLing();
        } else if("特斯拉".equals(car)){
            return new Tesla();
        } else {
            return null;
        }

    }

    // 方法二
    public static Car getWuLing(){
        return new WuLing();
    }

    public static Car getTesla(){
        return new Tesla();
    }
}
