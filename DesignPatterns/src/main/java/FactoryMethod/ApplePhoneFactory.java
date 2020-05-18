package FactoryMethod;

/**
 * @author liuzy
 * @date 2020/5/19 00:06
 */
public class ApplePhoneFactory implements Factory {
    @Override
    public Phone create() {
        System.out.println("ApplePhone create......");
        return new ApplePhone();
    }
}
