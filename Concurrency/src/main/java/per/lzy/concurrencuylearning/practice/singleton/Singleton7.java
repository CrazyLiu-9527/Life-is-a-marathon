package per.lzy.concurrencuylearning.practice.singleton;

/**
 * 静态内部类方式，可用
 *
 * @author liuzy
 * @date 2020/7/26 20:31
 */
public class Singleton7 {

    private Singleton7() {

    }

    public static Singleton7 getInstance() {
        return SingletonInstance.INSTANCE;
    }

    public static class SingletonInstance {
        private static final Singleton7 INSTANCE = new Singleton7();
    }
}
