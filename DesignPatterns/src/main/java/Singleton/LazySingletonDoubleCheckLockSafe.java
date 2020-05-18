package Singleton;

/**
 * 线程安全的懒汉式
 * 是LazySingletonSynchronizedSafe的优化方案
 * 需要注意的有两点
 * 一、 instance 需要使用 volatile 关键修饰，用于禁止对象在创建过程中出现指令重排序。通常对象的创建分为以下三步：
 * 给对象分配内存空间；
 * 1、调用对象的构造器方法，
 * 2、并执行初始化操作；
 * 3、将变量指向相应的内存地址。
 * 如果没有禁止指令重排序，则 2 ，3 步可能会发生指令重排序，这在单线程下是没有问题的，也符合 As-If-Serial 原则，
 * 但是如果在多线程下就会出现线程不安全的问题，因此需要加volatile关键字禁止指令重排序
 * 二、加锁过程需要两次判断，也就是我们常说的DCL（double check lock）
 * 保证多线程环境下的安全性
 *
 * @author liuzy
 * @date 2020/5/18 22:43
 */
public class LazySingletonDoubleCheckLockSafe {
    private static volatile LazySingletonDoubleCheckLockSafe INSTANCE;

    private LazySingletonDoubleCheckLockSafe() {
    }

    public static LazySingletonDoubleCheckLockSafe getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingletonDoubleCheckLockSafe.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new LazySingletonDoubleCheckLockSafe();
                }
            }
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingletonDoubleCheckLockSafe.getInstance().hashCode());
            }).start();
        }
    }
}
