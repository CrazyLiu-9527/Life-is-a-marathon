package Strategy;

/**
 * @author liuzy
 * @date 2020/5/19 22:12
 */
public class GreenPenStrategy implements Strategy {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("使用绿色画笔画...");
    }
}
