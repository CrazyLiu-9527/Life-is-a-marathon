package per.lzy.concurrencuylearning.core.createmethods_01;


/**
 * 同时实现runnable和继承thread类来创建线程
 *
 * @author liuzy
 * @date 2020/7/25 16:44
 */
public class BothRunnableThread {

    /*
     会发现运行的是继承thread类的方法，
     因为整个重写了thread的run方法，不再执行run方法中if(target==null)的判断，target指的是runnable的实例，具体看源码
    */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
