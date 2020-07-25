package per.lzy.concurrencuylearning.core.createmethods_01.wrongways;

/**
 * lambda表达式创建线程
 * 只是一种写法上的简化
 *
 * @author liuzy
 * @date 2020/7/25 16:57
 */
public class LambdaDemo {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
