package per.lzy.concurrencuylearning.core.synchronizeddemo;

/**
 * @author zhiyuanliu
 * @date 2020/7/13 14:49
 */
public class SyncTest {

    // 使用object作为锁(任何对象都有对应的锁标记，object也不例外)
    private Object object = new Object();

    // 一、synchronized修饰普通方法，此时锁的是 SyncTest 这个对象（内置锁）
    public synchronized void test1() {
        // doSomething
    }

    public void test2() {
        // 二、synchronized修饰代码块，此时锁的是 SyncTest 这个对象（内置锁）-->this
        synchronized (this) {
            // doSomething
        }
    }

    public void test3() {
        // 三、synchronized修饰代码块，此时锁的是自己创建的object
        // 这种锁的方式（随便使用一个对象作为锁）称之为客户端锁，是不建议使用的
        synchronized (object) {
            // doSomething
        }
    }

    // 四、修饰静态方法代码块，静态方法属于类方法，它属于这个类，获取到的锁是属于类的锁（类的字节码文件对象）-->SyncTest.class
    public static synchronized void test4() {
        // doSomething
    }

    /**
     * 五、synchronized的锁是可重入的，查看以下例子
     * 1、当线程A进入到LoggingWidget的doSomething()方法时，此时拿到了LoggingWidget实例对象的锁。
     * 2、随后在方法上又调用了父类Widget的doSomething()方法，它又是被synchronized修饰。
     * 3、那现在我们LoggingWidget实例对象的锁还没有释放，进入父类Widget的doSomething()方法还需要一把锁吗？
     * 不需要的！
     *
     * 因为锁的持有者是“线程”，而不是“调用”。线程A已经是有了LoggingWidget实例对象的锁了，当再需要的时候可以继续“开锁”进去的！
     * 这就是内置锁的可重入性。记住，持有锁的是线程。
     */
    // SyncTest中一个加锁的方法
    public synchronized void doSomething() {

    }

    public class LoggingSyncTest extends SyncTest {
        public synchronized void logging() {
            System.out.println(toString() + ": calling doSomething");
            super.doSomething();
        }
    }

    /**
     * synchronized修饰静态方法获取的是类锁(类的字节码文件对象)，synchronized修饰普通方法或代码块获取的是对象锁。
     * 它俩是不冲突的，也就是说：获取了类锁的线程和获取了对象锁的线程是不冲突的！
     * 参考以下例子
     */
    // synchronized修饰非静态方法
    public synchronized void function() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("function running...");
        }
    }

    // synchronized修饰静态方法
    public static synchronized void staticFunction()
            throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            System.out.println("Static function running...");
        }
    }

    public static void main(String[] args) {
        final SyncTest syncTest = new SyncTest();
        // 创建线程执行静态方法
        Thread t1 = new Thread(() -> {
            try {
                staticFunction();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 创建线程执行实例方法
        Thread t2 = new Thread(() -> {
            try {
                syncTest.function();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // 启动
        t1.start();
        t2.start();
    }
}
