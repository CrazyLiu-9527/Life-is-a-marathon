package per.lzy.springlearning.service;


import per.lzy.springlearning.model.enums.EnumAnimalType;

/**
 * 动物接口
 *
 * @author zhiyuanliu
 * @date 2020/6/30 17:35
 */
public interface AnimalService {
    /**
     * 动物类型
     *
     * @return EnumAnimalType
     */
    EnumAnimalType getType();

    /**
     * 动物行为 吃饭
     */
    void eat();

    /**
     * 动物行为 睡觉
     */
    void sleep();

    /**
     * 动物行为 奔跑
     */
    void run();

    /**
     * 动物行为 发出叫声
     */
    void bark();
}
