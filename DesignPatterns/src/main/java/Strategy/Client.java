package Strategy;

/**
 * @author liuzy
 * @date 2020/5/19 22:15
 */
public class Client {
    private Strategy strategy;

    public Client(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeDraw(int radius, int x, int y) {
        strategy.draw(radius, x, y);
    }
}
