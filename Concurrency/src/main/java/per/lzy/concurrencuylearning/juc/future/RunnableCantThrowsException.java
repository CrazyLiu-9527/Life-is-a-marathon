package per.lzy.concurrencuylearning.juc.future;

/**
 * 在run方法中无法抛出checked Exception
 *
 * @author zhiyuanliu
 * @date 2020/8/13 15:06
 */
public class RunnableCantThrowsException {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /*try {
                    ddd();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        };
    }

    public static void ddd() throws Exception {

    }
}
