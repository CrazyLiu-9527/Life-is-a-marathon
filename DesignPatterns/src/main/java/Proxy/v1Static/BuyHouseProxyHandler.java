package Proxy.v1Static;

/**
 * @author zhiyuanliu
 * @date 2020/5/22 15:41
 */
public class BuyHouseProxyHandler implements BuyHouse {
    private BuyHouse buyHouse;

    public BuyHouseProxyHandler(BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buyHouse() {
        System.out.println("买房前准备");
        buyHouse.buyHouse();
        System.out.println("买房后装修");
    }
}
