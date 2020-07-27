package per.lzy.concurrencuylearning.core.background;

/**
 * 即使代码中不显示创建线程，在运行main时，JVM也会自动启动其他的线程，用断点debug查看
 *
 * @author zhiyuanliu
 * @date 2020/7/27 14:35
 */
public class JavaAndThreads {

    public static void main(String[] args) {
        System.out.println("Hello Threads!");
    }
}
