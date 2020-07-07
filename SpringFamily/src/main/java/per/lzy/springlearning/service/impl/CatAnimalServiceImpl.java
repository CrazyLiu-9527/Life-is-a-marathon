package per.lzy.springlearning.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import per.lzy.springlearning.commons.aop.MyAspectPoint;
import per.lzy.springlearning.model.enums.EnumAnimalType;
import per.lzy.springlearning.service.AnimalService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 17:38
 */
@Service
// 使用primary设置默认注入的bean
@Primary
// 使用order为bean指定注入顺序，这样使用list注入同一类型的bean时是有序的
@Order(1)
public class CatAnimalServiceImpl implements AnimalService {
    /**
     * 动物类型
     *
     * @return EnumAnimalType
     */
    @Override
    public EnumAnimalType getType() {
        return EnumAnimalType.CAT;
    }

    /**
     * 动物行为 吃饭
     */
    @Override
    @MyAspectPoint
    public void eat() {
        System.out.println("cat eat...");
    }

    /**
     * 动物行为 睡觉
     */
    @Override
    public void sleep() {
        System.out.println("cat sleep...");
    }

    /**
     * 动物行为 奔跑
     */
    @Override
    public void run() {
        System.out.println("cat run...");
    }

    /**
     * 动物行为 发出叫声
     */
    @Override
    public void bark() {
        System.out.println("cat bark...");
    }

    /**
     * Spring容器会对上述Bean做如下初始化流程：
     *
     * 调用构造方法创建CatAnimalServiceImpl实例；
     * 根据@Autowired进行注入；
     * 调用标记有@PostConstruct的init()方法进行初始化。
     */

    // bean初始化时会调用该方法
    @PostConstruct
    public void init() {
        System.out.println("Init");
    }

    // bean销毁时会调用该方法
    @PreDestroy
    public void destroy() {
        System.out.println("Shutdown");
    }
}
