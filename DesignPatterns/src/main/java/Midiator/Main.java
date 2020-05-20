package Midiator;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 20:53
 */
public class Main {
    public static void main(String[] args) {
        HouseMediator houseMediator = new HouseMediator();
        Person seller = new Person("卖方");
        Person buyer = new Person("买方");
        houseMediator.register(seller);
        houseMediator.register(buyer);
        buyer.send("价格多少");
        seller.send("10万");
        buyer.send("太贵了");
    }
}
