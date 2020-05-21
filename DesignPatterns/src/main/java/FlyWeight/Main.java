package FlyWeight;

/**
 * @author zhiyuanliu
 * @date 2020/5/21 15:09
 */
public class Main {
    public static void main(String[] args) {
        PingPangPool pingPangPool = new PingPangPool();

        for (int i = 0; i < 20; i++) {
            PingPang pingPang = pingPangPool.getPingPang();
            System.out.println(pingPang);
        }
    }
}
