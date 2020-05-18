package Singleton;

/**
 * 静态内部类方式
 * JVM保证单例
 * 加载外部类时不会加载内部类，这样可以实现懒加载
 *
 * @author liuzy
 * @date 2020/5/18 22:56
 */
public class StaticInnerClassHungrySingleton {

    private StaticInnerClassHungrySingleton() {

    }

    static class StaticInnerClassHungrySingletonHolder {
        private static final StaticInnerClassHungrySingleton INSTANCE = new StaticInnerClassHungrySingleton();
    }

    public static StaticInnerClassHungrySingleton getInstance() {
        return StaticInnerClassHungrySingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(LazySingletonDoubleCheckLockSafe.getInstance().hashCode());
            }).start();
        }
    }
}
