package com.example.singleton;

/**
 * lazy loading
 * 虽然到达了按需初始化的目的，但是却带来了线程不安全的问题
 * 可以通过synchronized解决，但是 带来了 效率下降
 */
public class Demo04 {

    private static Demo04 INSTANCE;

    private Demo04(){

    }

    public static synchronized Demo04 getInstance(){
        if (INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Demo04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 同一个类的不同对象 哈希值是不同的
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Demo04.getInstance().hashCode());
            }).start();
        }

    }

}
