package per.lzy.concurrencuylearning.juc.mythreadpool.callabledemo;

import java.util.concurrent.Callable;

/**
 * Callable就是Runnable的扩展
 * Runnable没有返回值，不能抛出受检查的异常，而Callable可以！
 *
 * @author zhiyuanliu
 * @date 2020/7/14 19:44
 */
public class MyCallable implements Callable<Integer> {

    private int number;

    public MyCallable(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < number; i++) {
            sum += i;
        }
        return sum;
    }
}
