package Spring.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhiyuanliu
 * @date 2020/5/19 20:37
 */
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_config.xml");
        Bean bean = (Bean) context.getBean("bean");
        bean.doSomething();
    }

}
