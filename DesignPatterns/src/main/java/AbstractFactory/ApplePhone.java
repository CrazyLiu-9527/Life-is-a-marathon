package AbstractFactory;

/**
 * @author liuzy
 * @date 2020/5/18 23:13
 */
public class ApplePhone implements Phone {

    @Override
    public void call() {
        System.out.println("ApplePhone call......");
    }
}
