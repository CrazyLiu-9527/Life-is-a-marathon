package per.lzy.concurrencuylearning.core.singleton;

/**
 * 懒汉式（线程安全）（不推荐）
 *
 * @author liuzy
 * @date 2020/7/26 20:25
 */
public class Singleton4 {
    private static Singleton4 INSTANCE;

    private Singleton4() {

    }

    public static synchronized Singleton4 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton4();
        }
        return INSTANCE;
    }
}
