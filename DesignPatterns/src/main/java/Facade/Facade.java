package Facade;

/**
 * 门面
 *
 * @author zhiyuanliu
 * @date 2020/5/20 19:58
 */
public class Facade {
    private Shape circle;
    private Shape rectangle;
    private Shape triangle;

    public Facade() {
        this.circle = new CircleShape();
        this.rectangle = new RectangleShape();
        this.triangle = new TriangleShape();
    }

    /**
     * 下面定义一堆方法，具体应该调用什么方法，由这个门面来决定
     */

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawTriangle() {
        triangle.draw();
    }

}
