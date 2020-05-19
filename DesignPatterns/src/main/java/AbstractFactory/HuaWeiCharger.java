package AbstractFactory;

/**
 * @author zhiyuanliu
 * @date 2020/5/19 19:28
 */
public class HuaWeiCharger implements Charger {
    @Override
    public void charge() {
        System.out.println("HuaWeiPhone charging......");
    }
}
