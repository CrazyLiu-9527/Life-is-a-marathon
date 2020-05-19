package Spring.v1;

/**
 * @author zhiyuanliu
 * @date 2020/5/19 20:38
 */
public class Bean {
    public void doSomething() {
        System.out.println("doing...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
