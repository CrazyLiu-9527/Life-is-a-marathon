package per.lzy.springlearning.commons.helper;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import per.lzy.springlearning.model.enums.EnumAnimalType;
import per.lzy.springlearning.service.AnimalService;

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

    private ApplicationContext applicationContext;

    private static Map<EnumAnimalType, AnimalService> animalServiceMap;

    static {
        animalServiceMap = new HashMap<>();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        Map<String, AnimalService> map = applicationContext.getBeansOfType(AnimalService.class);
        System.out.println(JSONObject.toJSONString(animalServiceMap));

        for (String key : map.keySet()) {
            animalServiceMap.put(map.get(key).getType(), map.get(key));
        }
    }

    /**
     * 通过传入的类型返回对应的bean
     *
     * @param type
     * @return
     */
    private static AnimalService getAnimalServiceByType(EnumAnimalType type) {
        return animalServiceMap.get(type);
    }

    /**
     * 从context中获取指定名称的bean实例
     *
     * @param beanName bean名称
     * @return bean实例
     */
    public Object getBean(String beanName) {
        return this.applicationContext.getBean(beanName);
    }
}
