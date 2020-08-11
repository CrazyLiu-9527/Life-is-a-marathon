package per.lzy.concurrencuylearning.juc.cas;

/**
 * 模拟CAS操作，等价代码
 *
 * @author zhiyuanliu
 * @date 2020/8/11 11:36
 */
public class SimulateCAS {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
