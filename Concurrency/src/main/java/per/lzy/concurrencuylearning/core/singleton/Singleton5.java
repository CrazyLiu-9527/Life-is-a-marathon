package per.lzy.concurrencuylearning.core.singleton;

/**
 * 懒汉式（线程不安全）（不推荐）
 *
 * @author liuzy
 * @date 2020/7/26 20:26
 */
public class Singleton5 {
    private static Singleton5 INSTANCE;

    private Singleton5() {

    }

    public static Singleton5 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton5.class) {
                INSTANCE = new Singleton5();
            }
        }

        return INSTANCE;
    }
}
