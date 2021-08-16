package com.example.singleton;

/**
 * lazy loading
 * DCL懒汉式(双重检测锁模式)
 * 虽然到达了按需初始化的目的，但是却带来了线程不安全的问题
 * 可以通过synchronized解决，但是 带来了 效率下降
 */
public class Demo06 {

    private static volatile Demo06 INSTANCE;

    private Demo06(){

    }

    public static Demo06 getInstance(){
        // 外层if是有必要的 因为大多数线程进来后直接就过了 不会上锁
        if (INSTANCE == null){
            // 双重检查
            synchronized (Demo05.class){
                if(INSTANCE == null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Demo06();
                    /**
                     * 不是原子性操作
                     * 1. 分配内存空间
                     * 2. 执行构造方法 初始化对象
                     * 3. 把对象指向 内存空间
                     *
                     * 会进行指令重排
                     * A 线程的执行顺序可能是1 3 2
                     *   当A 执行完3 还没执行2的时候  线程B进来执行  由于执行完3 会认为INSTANCE
                     *   不为null(因为已经指向了内存空间) 所以B会返回未完成构造的INSTANCE
                     *   需要在INSTANCE上加volatile修饰
                     *
                     * volatile：对于同一个变量，在一个线程中值发生了改变，则在另一个线程中立即生效，可以大幅度避免下面的问题，不排除极端情况
                     */
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 同一个类的不同对象 哈希值是不同的
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Demo06.getInstance().hashCode());
            }).start();
        }

    }
}
