package per.lzy.concurrencuylearning.core.threadcoreknowledge.threadobjectclasscommonmethods_05;

/**
 * @author liuzy
 * @date 2020/7/12 22:19
 */
public class ThreadMethods_yield implements Runnable {
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 5000000; i++) {
            count = count + i;
            // 试着对比注释掉下面一行和不注释下面一行运行时间的对比
//            Thread.yield();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("用时：" + (endTime - beginTime) + "毫秒");
    }

    /**
     * 调用yield方法会让当前线程交出CPU权限，让CPU去执行其他的线程。它跟sleep方法类似，同样不会释放锁。
     * 但是yield不能控制具体的交出CPU的时间，另外，yield方法只能让拥有相同优先级的线程有获取CPU执行时间的机会。
     * 注意，调用yield方法并不会让线程进入阻塞状态，而是让线程重回就绪状态，它只需要等待重新获取CPU执行时间，这一点是和sleep方法不一样的。
     *
     * @param args
     */
    public static void main(String[] args) {

        ThreadMethods_yield runnable = new ThreadMethods_yield();

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
