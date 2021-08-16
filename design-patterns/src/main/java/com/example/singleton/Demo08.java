package com.example.singleton;

/**
 * 不仅可以解决线程同步，还可以防止反序列化(反序列化不成枚举，因为枚举类没有构造方法即使拿到class文件也没有办法构造对象)
 * 即使反序列化也只能反序列化成 INSTANCE 这个值，再根据 INSTANCE 这个值产生对象返回的是和单例创建的同一个对象
 */
public enum Demo08 {

    INSTANCE;

    public void other(){
        System.out.println("其他方法");
    }

    public static void main(String[] args) {
        // 同一个类的不同对象 哈希值是不同的
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Demo08.INSTANCE.hashCode());
            }).start();
        }

    }
}
