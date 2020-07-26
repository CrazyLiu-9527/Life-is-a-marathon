package per.lzy.concurrencuylearning.core.threadcoreknowledge.stopthread_03;

/**
 * run无法抛出checked Exception，只能用try/catch
 *
 * @author liuzy
 * @date 2020/7/25 17:58
 */
public class RunThrowException {

    public static void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
