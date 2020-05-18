package Singleton;

/**
 * 普通的懒汉式
 * 虽然达到了按需初始化的目的，但是却带来了线程不安全的问题
 *
 * @author liuzy
 * @date 2020/5/18 22:13
 */
public class LazySingletonUnsafe {
    private static LazySingletonUnsafe INSTANCE;

    private LazySingletonUnsafe() {

    }

    public static LazySingletonUnsafe getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new LazySingletonUnsafe();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingletonUnsafe.getInstance().hashCode());
            }).start();
        }
    }
}
