package Strategy;

/**
 * @author liuzy
 * @date 2020/5/19 22:17
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 调用方无需关心各个策略的具体实现，只需要针对不同的情况选用不同的策略
         */
        Client client = new Client(new BluePenStrategy());
        client.executeDraw(10, 1, 1);
    }
}
