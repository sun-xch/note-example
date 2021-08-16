package com.example.singleton;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单使用，推荐使用！
 * 唯一缺点：不管用到与否，类装载时就完成实例化(即使 不使用)
 */
public class Demo01 {

    //1.私有化类的构造器
    private Demo01(){

    }

    //2.内部创建类的对象
    //4.要求此对象也必须声明为静态的
    private static final Demo01 INSTANCE = new Demo01();

    //3.提供公共的静态方法，返回类的对象
    public static Demo01 getInstance(){
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        Demo01 instance_1 = Demo01.getInstance();
        Demo01 instance_2 = Demo01.getInstance();
        System.out.println(instance_1 == instance_2);
    }
}
