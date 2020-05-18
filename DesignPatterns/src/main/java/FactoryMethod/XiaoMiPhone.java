package FactoryMethod;

/**
 * @author liuzy
 * @date 2020/5/18 23:11
 */
public class XiaoMiPhone implements Phone {

    @Override
    public void call() {
        System.out.println("XiaoMiPhone call......");
    }
}
