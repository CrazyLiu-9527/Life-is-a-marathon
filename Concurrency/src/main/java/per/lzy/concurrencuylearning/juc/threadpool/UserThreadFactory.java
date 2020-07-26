package per.lzy.concurrencuylearning.juc.threadpool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 *
 * @author zhiyuanliu
 * @date 2020/7/14 19:55
 */
public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger(0);

    //【强制】创建线程或线程池时请指定有意义的线程名称，方便出错时回溯。
    //`正例：`自定义线程工厂，并且根据外部特征进行分组，比如，来自同一机房的调用，把机房编号赋值给`whatFeaturOfGroup`
    public UserThreadFactory(String whatFeatureOfGroup) {
        this.namePrefix = "From UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
    }

    /**
     * Constructs a new {@code Thread}.  Implementations may also initialize
     * priority, name, daemon status, {@code ThreadGroup}, etc.
     *
     * @param r a runnable to be executed by new thread instance
     * @return constructed thread, or {@code null} if the request to
     * create a thread is rejected
     */
    @Override
    public Thread newThread(Runnable r) {
        String name = namePrefix + nextId.getAndIncrement();
        Thread thread = new Thread(null, r, name, 0);
        System.out.println(thread.getName());
        return thread;
    }
}
