package com.example.singleton;

/**
 * 与Demo01 一样，只是对象创建使用了静态代码块
 */
public class Demo02 {

    private static final Demo02 INSTANCE;

    static {
        INSTANCE = new Demo02();
    }

    private Demo02(){

    }

    public static Demo02 getInstance(){
        return INSTANCE;
    }

    public static void main(String[] args) {
        Demo02 instance_1 = Demo02.getInstance();
        Demo02 instance_2 = Demo02.getInstance();
        System.out.println(instance_1 == instance_2);
    }
}
