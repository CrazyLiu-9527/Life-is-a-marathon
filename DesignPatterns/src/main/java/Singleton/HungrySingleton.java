package Singleton;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用！
 * 唯一缺点：不管用到与否，类装载时就完成实例化
 * Class.forName("")
 * （话说你不用的，你装载它干啥）
 *
 * @author liuzy
 * @date 2020/5/18 22:05
 */
public class HungrySingleton {

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    //将默认构造方法私有化
    private HungrySingleton() {
    }

    private static HungrySingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        HungrySingleton h1 = HungrySingleton.getInstance();
        HungrySingleton h2 = HungrySingleton.getInstance();
        System.out.println(h1 == h2);
    }
}
