package FactoryMethod;

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
}
