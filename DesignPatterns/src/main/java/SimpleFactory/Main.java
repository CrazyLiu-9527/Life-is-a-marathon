package SimpleFactory;

/**
 * @author liuzy
 * @date 2020/5/18 23:12
 */
public class Main {

    public static void main(String[] args) {
        /**
         * 第一种方式
         * 每次想要一个类型的手机都需要new 不同的类
         */
        Phone phone = new ApplePhone();
        phone.call();
        phone = new HuaWeiPhone();
        phone.call();
        phone = new XiaoMiPhone();
        phone.call();

        /**
         * 第二种方式
         * 将类的创建交给PhoneFactory这个工厂，想要获取不同的类只需要调用工厂的不同方法
         * 优点：屏蔽了对象的创建过程，使用户不必关心具体的创建细节
         * 缺点：可扩展性差，当新增加一个类型的时候，仍然需要在PhoneFactory中增加或修改代码，违反了开闭原则，因此简单工厂模式并不是23种设计模式中的一种
         */
        Phone phone2 = PhoneFactory.getApplePhone();
        phone2.call();
        phone2 = PhoneFactory.getHuaWeiPhone();
        phone2.call();
        phone2 = PhoneFactory.getXiaoMiPhone();
        phone2.call();
    }

}
