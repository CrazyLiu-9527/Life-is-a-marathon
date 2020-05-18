package Singleton;

/**
 * 线程不安全的懒汉式
 * 本意是LazySingletonSynchronizedSafe的优化方案，判断一次instance不为空再加锁，提升效率
 * 但是仅靠一次为null的判断在多线程环境下还是可能导致创建时生成多个对象
 *
 * @author liuzy
 * @date 2020/5/18 22:35
 */
public class LazySingletonSynchronizedUnsafe {
    private static LazySingletonSynchronizedUnsafe INSTANCE;

    private LazySingletonSynchronizedUnsafe() {
    }

    public static LazySingletonSynchronizedUnsafe getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingletonSynchronizedUnsafe.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new LazySingletonSynchronizedUnsafe();
            }
        }

        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingletonSynchronizedUnsafe.getInstance().hashCode());
            }).start();
        }
    }
}
