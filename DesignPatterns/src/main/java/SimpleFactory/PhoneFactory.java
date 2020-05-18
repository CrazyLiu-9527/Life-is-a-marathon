package SimpleFactory;

/**
 * @author liuzy
 * @date 2020/5/18 23:17
 */
public class PhoneFactory {

    public static ApplePhone getApplePhone() {
        //before 可以增加业务逻辑
        return new ApplePhone();
    }

    public static HuaWeiPhone getHuaWeiPhone() {
        return new HuaWeiPhone();
    }

    public static XiaoMiPhone getXiaoMiPhone() {
        return new XiaoMiPhone();
    }
}
