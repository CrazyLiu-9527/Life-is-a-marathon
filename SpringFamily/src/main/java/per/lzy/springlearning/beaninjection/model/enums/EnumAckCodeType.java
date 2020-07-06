package per.lzy.springlearning.beaninjection.model.enums;

/**
 * ack code
 * @author zhiyuanliu
 * @date 2020/6/30 17:17
 */
public enum EnumAckCodeType {
    /**
     * 请求成功
     */
    SUCCESS(0),
    /**
     * 请求失败
     */
    FAILURE(1),
    /**
     * 警告
     */
    WARNING(2),
    /**
     * 部分失败
     */
    PARTIAL_FAILURE(3);

    private final int value;

    private EnumAckCodeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
