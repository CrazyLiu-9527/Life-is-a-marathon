package AbstractFactory;

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

    @Override
    public Charger produce() {
        System.out.println("XiaoMiCharger create......");
        return new XiaoMiCharger();
    }

    @Override
    public HeadSet make() {
        System.out.println("XiaoMiHeadSet make......");
        return new XiaoMiHeadSet();
    }
}
