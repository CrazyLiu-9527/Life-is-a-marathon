package per.lzy.concurrencuylearning.core.threadcoreknowledge.createmethods_01.wrongways;

/**
 * 匿名内部类的方式创建线程
 * 只是写法变了而已
 *
 * @author liuzy
 * @date 2020/7/25 17:05
 */
public class AnonymousInnerClassDemo {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }

}
