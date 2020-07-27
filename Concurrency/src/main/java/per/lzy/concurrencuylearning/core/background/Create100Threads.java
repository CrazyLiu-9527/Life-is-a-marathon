package per.lzy.concurrencuylearning.core.background;

/**
 * 创建100个线程，用活动监视器的CPU栏目看Java线程数量的变化，10秒后线程消失了。
 *
 * @author zhiyuanliu
 * @date 2020/7/27 14:33
 */
public class Create100Threads {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
