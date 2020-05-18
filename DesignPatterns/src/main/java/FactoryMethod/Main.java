package FactoryMethod;

/**
 * @author liuzy
 * @date 2020/5/18 23:12
 */
public class Main {

    /**
     * 可扩展性好
     * 增加新的手机类只需要新增一个手机类和一个用于创建该手机的工厂类
     * 不会对现有代码造成改动
     *
     * @param args
     */
    public static void main(String[] args) {
        Factory factory = new XiaoMiPhoneFactory();
        Phone phone = factory.create();
        factory = new HuaWeiPhoneFactory();
        phone = factory.create();
        factory = new ApplePhoneFactory();
        phone = factory.create();
    }

}
