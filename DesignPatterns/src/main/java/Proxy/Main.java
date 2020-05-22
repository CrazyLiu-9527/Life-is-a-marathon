package Proxy;

import Proxy.v1Static.BuyHouse;
import Proxy.v1Static.BuyHouseImpl;
import Proxy.v1Static.BuyHouseProxyHandler;
import Proxy.v2JDK.DynamicProxyHandler;
import Proxy.v3Cglib.CglibProxyHandler;

import java.lang.reflect.Proxy;

/**
 * @author zhiyuanliu
 * @date 2020/5/22 15:42
 */
public class Main {

    public static void main(String[] args) {
        /**
         * 直接调用
         */
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHouse();
        System.out.println("===================================");

        /**
         * 静态调用
         */
        BuyHouseProxyHandler buyHouseProxyHandler = new BuyHouseProxyHandler(buyHouse);
        buyHouseProxyHandler.buyHouse();
        System.out.println("===================================");

        /**
         * jdk动态代理
         */
        BuyHouse proxyBuyHouse = (BuyHouse) Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), new Class[]{BuyHouse.class}, new DynamicProxyHandler(buyHouse));
        proxyBuyHouse.buyHouse();
        System.out.println("===================================");

        /**
         * cglib动态代理
         */
        CglibProxyHandler cglibProxyHandler = new CglibProxyHandler();
        BuyHouse buyHouseCglibProxy = (BuyHouseImpl) cglibProxyHandler.getInstance(buyHouse);
        buyHouseCglibProxy.buyHouse();


        /**
         * 代理模式的定义：代理模式给某一个对象提供一个代理对象，并由代理对象控制对原对象的引用。通俗的来讲代理模式就是我们生活中常见的中介。
         */
        /**
         * 动态代理总结：虽然相对于静态代理，动态代理大大减少了我们的开发任务，同时减少了对业务接口的依赖，降低了耦合度。
         * 但是还是有一点点小小的遗憾之处，那就是它始终无法摆脱仅支持interface代理的桎梏，因为它的设计注定了这个遗憾。
         * 回想一下那些动态生成的代理类的继承关系图，它们已经注定有一个共同的父类叫Proxy。Java的继承机制注定了这些动态
         * 代理类们无法实现对class的动态代理，原因是多继承在Java中本质上就行不通。有很多条理由，人们可以否定对 class代理
         * 的必要性，但是同样有一些理由，相信支持class动态代理会更美好。接口和类的划分，本就不是很明显，只是到了Java中才变
         * 得如此的细化。如果只从方法的声明及是否被定义来考量，有一种两者的混合体，它的名字叫抽象类。实现对抽象类的动态代理，
         * 相信也有其内在的价值。此外，还有一些历史遗留的类，它们将因为没有实现任何接口而从此与动态代理永世无缘。如此种种，不得
         * 不说是一个小小的遗憾。但是，不完美并不等于不伟大，伟大是一种本质，Java动态代理就是佐例。
         */
    }
}
