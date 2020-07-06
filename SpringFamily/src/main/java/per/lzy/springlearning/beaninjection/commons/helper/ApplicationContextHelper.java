package per.lzy.springlearning.beaninjection.commons.helper;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import per.lzy.springlearning.beaninjection.model.enums.EnumAnimalType;
import per.lzy.springlearning.beaninjection.service.AnimalService;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作spring上下文工具
 *
 * @author zhiyuanliu
 * @date 2020/6/30 17:01
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    // 保存animalService的所有bean在spring上下文中
    private static Map<EnumAnimalType, AnimalService> animalServiceMap;

    static {
        animalServiceMap = new HashMap<>();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHelper.applicationContext = applicationContext;

        Map<String, AnimalService> map = applicationContext.getBeansOfType(AnimalService.class);
        for (String key : map.keySet()) {
            animalServiceMap.put(map.get(key).getType(), map.get(key));
        }
    }

    /**
     * 通过传入的类型返回对应的bean
     *
     * @param type animal类型
     * @return animal实例
     */
    public static AnimalService getAnimalServiceByType(EnumAnimalType type) {
        return animalServiceMap.get(type);
    }

    /**
     * 获取上下文
     *
     * @return spring上下文
     */
    public static ApplicationContext getContext() {
        return applicationContext;
    }

    /**
     * 从context中获取指定名称的bean实例
     *
     * @param beanName bean名称
     * @return bean实例
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz bean class
     * @param <T>   泛型
     * @return bean实例
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name  bean名称
     * @param clazz bean class
     * @param <T>   泛型
     * @return bean实例
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 获取一个接口下的所有bean
     * @param clazz 接口类型
     * @param <T> 泛型
     * @return 接口map
     */
    public static <T> Map<String, T> getBeanMap(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }

}
