package com.example.singleton;

/**
 * lazy loading
 * 懒汉式
 * 虽然到达了按需初始化的目的，但是却带来了线程不安全的问题
 */
public class Demo03 {

    private static Demo03 INSTANCE;

    private Demo03(){

    }

    public static Demo03 getInstance(){
        if (INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Demo03();
        }
        return INSTANCE;
    }

    public void other(){
        System.out.println("其他方法");
    }

    public static void main(String[] args) {
        // 同一个类的不同对象 哈希值是不同的
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Demo03.getInstance().hashCode());
            }).start();
        }

//        for (int i = 0; i < 100; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Demo03.getInstance().hashCode());
//                }
//            }).start();
//        }

    }
}
