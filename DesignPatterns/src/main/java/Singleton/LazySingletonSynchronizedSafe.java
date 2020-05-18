package Singleton;

/**
 * 线程安全的懒汉式
 * 但是加了synchronized,导致每次getInstance都要加锁，而synchronized是重量级锁，因此会带来性能问题
 *
 * @author liuzy
 * @date 2020/5/18 22:30
 */
public class LazySingletonSynchronizedSafe {
    private static LazySingletonSynchronizedSafe INSTANCE;

    private LazySingletonSynchronizedSafe() {
    }

    public static synchronized LazySingletonSynchronizedSafe getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new LazySingletonSynchronizedSafe();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingletonSynchronizedSafe.getInstance().hashCode());
            }).start();
        }
    }
}
