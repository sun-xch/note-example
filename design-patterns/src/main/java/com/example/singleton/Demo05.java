package com.example.singleton;

/**
 * lazy loading
 * 虽然到达了按需初始化的目的，但是却带来了线程不安全的问题
 * 可以通过synchronized解决，但是 带来了 效率下降
 */
public class Demo05 {

    private static Demo05 INSTANCE;

    private Demo05(){

    }

    public static Demo05 getInstance(){
        if (INSTANCE == null){
            // 通过减小同步代码块的方式提高效率，然后不可行 线程不安全
            // 因为如果多个线程进到if中 当第一把锁释放后 进到if中的线程会继续创建对象
            synchronized (Demo05.class){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Demo05();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 同一个类的不同对象 哈希值是不同的
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Demo05.getInstance().hashCode());
            }).start();
        }

    }
}
