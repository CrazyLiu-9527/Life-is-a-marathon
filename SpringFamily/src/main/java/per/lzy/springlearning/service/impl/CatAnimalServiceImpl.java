package per.lzy.springlearning.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import per.lzy.springlearning.model.enums.EnumAnimalType;
import per.lzy.springlearning.service.AnimalService;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 17:38
 */
@Service
@Primary
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
}
