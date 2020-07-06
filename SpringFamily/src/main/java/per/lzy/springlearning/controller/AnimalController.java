package per.lzy.springlearning.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.lzy.springlearning.commons.helper.ApplicationContextHelper;
import per.lzy.springlearning.model.enums.EnumAnimalType;
import per.lzy.springlearning.service.AnimalService;
import per.lzy.springlearning.service.impl.CatAnimalServiceImpl;
import per.lzy.springlearning.service.impl.DogAnimalServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 21:29
 */
@RestController
public class AnimalController {

    // 注入实现了同一接口的不同bean
    /*@Autowired
    private CatAnimalServiceImpl catAnimalService;

    @Autowired
    private DogAnimalServiceImpl dogAnimalService;*/

    // 使用@Qualifier注解注入实现了统一接口的不同bean
    /*@Qualifier("catAnimalServiceImpl")
    @Autowired
    private AnimalService catAnimalService;

    @Qualifier("dogAnimalServiceImpl")
    @Autowired
    private AnimalService dogAnimalService;*/

    // 通过Autowired注解实现将同类型的所有bean注入到一个list中
    @Autowired
    List<AnimalService> animalServiceList;

    // 在catServiceImpl中，使用了@primary注解，使得在注入的时候默认就注入catServiceImpl
    @Autowired
    private AnimalService animalService;

    @RequestMapping("/animalInjectionTest")
    public void testAnimalInjection() {
//        catAnimalService.eat();
//        dogAnimalService.eat();
        animalService.eat();
        System.out.println("==================");

        // 通过beanName来获取对应的bean
        AnimalService catService = ApplicationContextHelper.getBean(EnumAnimalType.CAT.getBeanName());
        AnimalService dogService = ApplicationContextHelper.getBean(EnumAnimalType.DOG.getBeanName());
        catService.run();
        dogService.run();
        System.out.println("==================");

        // 通过bean class获取对应的bean
        AnimalService catServiceImpl = ApplicationContextHelper.getBean(CatAnimalServiceImpl.class);
        AnimalService dogServiceImpl = ApplicationContextHelper.getBean(DogAnimalServiceImpl.class);
        catServiceImpl.bark();
        dogServiceImpl.bark();
        System.out.println("==================");

        // 先将bean注入到spring上下文中，然后在需要的时候获取对应的bean
        AnimalService cat = ApplicationContextHelper.getAnimalServiceByType(EnumAnimalType.CAT);
        AnimalService dog = ApplicationContextHelper.getAnimalServiceByType(EnumAnimalType.DOG);
        cat.sleep();
        dog.sleep();
        System.out.println("==================");

        // 获取所有的AnimalService类型的bean，返回一个map
        Map<String, AnimalService> animalServiceMap = ApplicationContextHelper.getBeanMap(AnimalService.class);
        System.out.println(JSON.toJSONString(animalServiceMap));
        System.out.println("==================");

        // 将同类型的所有bean注入到一个list中
        System.out.println(JSON.toJSONString(animalServiceList));
        System.out.println("==================");
    }

}
