package AbstractFactory;

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

    @Override
    public Charger produce() {
        System.out.println("AppleCharger produce......");
        return new AppleCharger();
    }

    @Override
    public HeadSet make() {
        System.out.println("AppleHeadSet make......");
        return new AppleHeadSet();
    }
}
