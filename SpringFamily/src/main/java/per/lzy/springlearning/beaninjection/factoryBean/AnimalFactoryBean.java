package per.lzy.springlearning.beaninjection.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import per.lzy.springlearning.beaninjection.service.AnimalService;

/**
 * todo 使用factorybean创建bean
 *
 * @author zhiyuanliu
 * @date 2020/7/6 22:33
 */
public class AnimalFactoryBean implements FactoryBean<AnimalService> {
    @Override
    public AnimalService getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
