package Bridge;

/**
 * @author zhiyuanliu
 * @date 2020/5/26 17:02
 */
public class BluePen implements DrawApi {
    public void draw(int radius, int x, int y) {
        System.out.println("用蓝色笔画图，radius:" + radius + ", x:" + x + ", y:" + y);
    }
}
