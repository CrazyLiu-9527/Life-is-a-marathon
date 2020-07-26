package per.lzy.concurrencuylearning.core.singleton;

/**
 * 饿汉式（静态常量）（可用）
 *
 * @author liuzy
 * @date 2020/7/26 20:20
 */
public class Singleton1 {

    private static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }

    public static Singleton1 getInstance() {
        return INSTANCE;
    }
}
