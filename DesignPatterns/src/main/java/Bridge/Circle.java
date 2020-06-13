package Bridge;

/**
 * @author zhiyuanliu
 * @date 2020/5/26 17:03
 */
public class Circle extends Shape {
    private int radius;

    public Circle(int radius, DrawApi drawApi) {
        super(drawApi);
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawApi.draw(radius, 0, 0);
    }
}
