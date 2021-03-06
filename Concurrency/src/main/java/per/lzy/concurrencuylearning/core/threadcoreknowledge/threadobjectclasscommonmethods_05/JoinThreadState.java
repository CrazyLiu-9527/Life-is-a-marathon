package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

/**
 * 先join再mainThread.getState()
 * 通过debugger看线程join前后状态的对比
 *
 * @author liuzy
 * @date 2020/7/26 17:48
 */
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(3 * 1000);
                System.out.println(mainThread.getState());
                System.out.println("Thread-0运行结" +
                        "束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        thread.join();
        System.out.println("子线程运行完毕");
    }
}
