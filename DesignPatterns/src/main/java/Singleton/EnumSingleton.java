package Singleton;

/**
 * 枚举方式实现单例
 * 不仅可以解决线程同步，还可以防止反序列化
 *
 * @author liuzy
 * @date 2020/5/18 23:00
 */
public enum EnumSingleton {
    INSTANCE;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(EnumSingleton.INSTANCE.hashCode());
            }).start();
        }
    }
}
