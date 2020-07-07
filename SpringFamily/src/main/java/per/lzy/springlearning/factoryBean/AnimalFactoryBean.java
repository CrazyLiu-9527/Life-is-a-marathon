package per.lzy.springlearning.factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import per.lzy.springlearning.service.AnimalService;
import per.lzy.springlearning.service.impl.DogAnimalServiceImpl;

/**
 * FactoryBean的特殊之处在于它可以向容器中注册两个Bean，一个是它本身，一个是FactoryBean.getObject()方法返回值所代表的Bean
 *
 * @author zhiyuanliu
 * @date 2020/7/6 22:33
 */
@Component
public class AnimalFactoryBean implements FactoryBean<AnimalService> {
    @Override
    public AnimalService getObject() throws Exception {
        return new DogAnimalServiceImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return DogAnimalServiceImpl.class;
    }
}
