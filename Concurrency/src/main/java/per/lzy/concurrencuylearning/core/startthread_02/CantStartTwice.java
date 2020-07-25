package per.lzy.concurrencuylearning.core.startthread_02;

/**
 * 演示两次调用start方法的结果
 *
 * @author liuzy
 * @date 2020/7/25 17:10
 */
public class CantStartTwice {

    /*
    一个线程能调用两次`start()`方法吗？会发生什么情况？

        1. 一个线程只能调用一次start()方法，否则会抛出IllegalThreadStateException，因为start()方法的语句会检查线程状态，线程状态不为 NEW 则抛出异常
        2. start()是 synchronized 修饰的线程安全方法，不存在线程已启动但线程状态 threadStatus 还未修改的情况，所以也不用担心被调用两次
        3. 即使线程执行结束（TERMINATED）也不能再调用start方法，只有线程状态为 NEW 时才可以调用 start。线程池复用的线程是不退出的，复用的是Runnable实例，而不是对同一个线程调用了多次start()方法，见[第
     */
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
