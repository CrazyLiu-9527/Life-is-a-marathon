package AbstractFactory;

/**
 * @author liuzy
 * @date 2020/5/19 00:07
 */
public class HuaWeiPhoneFactory implements Factory {

    @Override
    public Phone create() {
        System.out.println("HuaWeiPhone create......");
        return new HuaWeiPhone();
    }

    @Override
    public Charger produce() {
        System.out.println("HuaWeiCharger produce......");
        return new HuaWeiCharger();
    }

    @Override
    public HeadSet make() {
        System.out.println("HuaWeiHeadSet make......");
        return new HuaWeiHeadSet();
    }
}
