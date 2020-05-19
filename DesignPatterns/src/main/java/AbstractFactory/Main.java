package AbstractFactory;

/**
 * @author liuzy
 * @date 2020/5/18 23:12
 */
public class Main {

    /**
     * 抽象工厂模式继承了工厂模式的优点，能用于存在多个产品的情况，但其对应的产品族必须相对固定，
     * 假设我们现在认为 手机 + 充电器 + 耳机 + 手机壳 才算一个可以对外出售的产品，则上面所有的工厂类都需要更改，
     * 但显然不是所有的手机都有配套的手机壳，手机 + 充电器 + 耳机 这个产品族是相对固定的。
     *
     * 因此抽象工厂对于产品族的扩展比较好，但是对于单个产品的扩展就不是很友好了
     * @param args
     */
    public static void main(String[] args) {
        Factory factory = new HuaWeiPhoneFactory();
        Phone phone = factory.create();
        Charger charger = factory.produce();
        HeadSet headSet = factory.make();
    }

}
