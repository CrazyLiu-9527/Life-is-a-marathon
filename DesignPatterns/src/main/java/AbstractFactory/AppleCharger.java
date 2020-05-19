package AbstractFactory;

/**
 * @author zhiyuanliu
 * @date 2020/5/19 19:27
 */
public class AppleCharger implements Charger {
    @Override
    public void charge() {
        System.out.println("ApplePhone charging......");
    }
}
