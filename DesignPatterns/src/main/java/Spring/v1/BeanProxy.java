package Spring.v1;

/**
 * @author zhiyuanliu
 * @date 2020/5/19 20:40
 */
public class BeanProxy {
    public void before() {
        System.out.println("method start.." + System.currentTimeMillis());
    }

    public void after() {
        System.out.println("method stop.." + System.currentTimeMillis());
    }
}
