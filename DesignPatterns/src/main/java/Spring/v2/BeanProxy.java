package Spring.v2;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author zhiyuanliu
 * @date 2020/5/19 20:40
 */
@Aspect
public class BeanProxy {
    @Before("execution (void Spring.v2.Bean.doSomething())")
    public void before() {
        System.out.println("method start.." + System.currentTimeMillis());
    }

    @After("execution (void Spring.v2.Bean.doSomething())")
    public void after() {
        System.out.println("method stop.." + System.currentTimeMillis());
    }
}
