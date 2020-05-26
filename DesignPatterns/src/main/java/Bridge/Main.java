package Bridge;

/**
 * @author zhiyuanliu
 * @date 2020/5/26 17:05
 */
public class Main {
    public static void main(String[] args) {
        Shape greenCircle = new Circle(10, new GreenPen());
        Shape redRectangle = new Rectangle(4, 8, new RedPen());

        greenCircle.draw();
        redRectangle.draw();
    }
}
