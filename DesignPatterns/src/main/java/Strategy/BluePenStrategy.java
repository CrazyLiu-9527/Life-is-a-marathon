package Strategy;

/**
 * @author liuzy
 * @date 2020/5/19 22:13
 */
public class BluePenStrategy implements Strategy {
    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("使用蓝色画笔画画...");
    }
}
