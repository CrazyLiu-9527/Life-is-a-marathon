package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

/**
 * 线程ID从1开始，JVM运行起来之后，我们创建的线程早已不是2
 *
 * @author zhiyuanliu
 * @date 2020/7/27 11:22
 */
public class ThreadId {

    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程的ID为" + Thread.currentThread().getId());
        System.out.println("线程的ID为" + thread.getId());
    }
}
