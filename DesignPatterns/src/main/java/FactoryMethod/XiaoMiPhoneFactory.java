package FactoryMethod;

/**
 * @author liuzy
 * @date 2020/5/19 00:08
 */
public class XiaoMiPhoneFactory implements Factory {
    @Override
    public Phone create() {
        System.out.println("XiaoMiPhone create......");
        return new XiaoMiPhone();
    }
}
