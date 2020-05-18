package FactoryMethod;

/**
 * @author liuzy
 * @date 2020/5/18 23:12
 */
public class HuaWeiPhone implements Phone {

    @Override
    public void call() {
        System.out.println("HuaWeiPhone call......");
    }
}
