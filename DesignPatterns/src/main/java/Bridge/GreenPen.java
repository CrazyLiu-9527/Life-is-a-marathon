package Bridge;

/**
 * @author zhiyuanliu
 * @date 2020/5/26 17:02
 */
public class GreenPen implements DrawApi {
    public void draw(int radius, int x, int y) {
        System.out.println("用绿色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}
