package Bridge;

/**
 * @author zhiyuanliu
 * @date 2020/5/26 17:01
 */
public class RedPen implements DrawApi {
    public void draw(int radius, int x, int y) {
        System.out.println("用红色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}
