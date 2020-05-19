package AbstractFactory;

/**
 * @author zhiyuanliu
 * @date 2020/5/19 19:29
 */
public class XiaoMiCharger implements Charger {
    @Override
    public void charge() {
        System.out.println("XiaoMiPhone charging......");
    }
}
