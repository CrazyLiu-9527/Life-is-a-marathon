package per.lzy.springlearning.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 动物枚举类
 * @author zhiyuanliu
 * @date 2020/6/30 17:39
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum EnumAnimalType {
    DOG(1, "狗"),
    CAT(2, "猫");

    private int val;
    private String desc;

    /**
     * 根据传入的值找到对应的动物类型
     * @param val
     * @return
     */
    public static EnumAnimalType getTypeByVal(int val) {
        for (EnumAnimalType value : EnumAnimalType.values()) {
            if(val == value.val) {
                return value;
            }
        }
        return null;
    }
}
