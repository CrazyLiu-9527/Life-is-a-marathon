package per.lzy.springlearning.service.impl;

import org.springframework.stereotype.Service;
import per.lzy.springlearning.model.enums.EnumAnimalType;
import per.lzy.springlearning.service.AnimalService;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 17:36
 */
@Service
public class DogAnimalServiceImpl implements AnimalService {
    /**
     * 动物类型
     *
     * @return EnumAnimalType
     */
    @Override
    public EnumAnimalType getType() {
        return EnumAnimalType.DOG;
    }

    /**
     * 动物行为 吃饭
     */
    @Override
    public void eat() {
        System.out.println("dog eat...");
    }

    /**
     * 动物行为 睡觉
     */
    @Override
    public void sleep() {
        System.out.println("dog sleep...");
    }

    /**
     * 动物行为 奔跑
     */
    @Override
    public void run() {
        System.out.println("dog run...");
    }

    /**
     * 动物行为 发出叫声
     */
    @Override
    public void bark() {
        System.out.println("dog bark...");
    }
}
