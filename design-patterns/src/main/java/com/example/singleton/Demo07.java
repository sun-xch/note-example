package com.example.singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 * 外部类被加载时 内部类是不会被加载的 当调用getInstance()时才会被加载
 * 线程安全由JVM保证，虚拟机在加载class时 只加载一次
 */
public class Demo07 {

    private Demo07(){

    }

    // 静态内部类
    private static class Demo07Holder{
        private final static Demo07 INSTANCE = new Demo07();
    }

    public static Demo07 getInstance(){
        return Demo07Holder.INSTANCE;
    }

    public void other(){
        System.out.println("其他方法");
    }

    public static void main(String[] args) {
        // 同一个类的不同对象 哈希值是不同的
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Demo07.getInstance().hashCode());
            }).start();
        }

    }
}
