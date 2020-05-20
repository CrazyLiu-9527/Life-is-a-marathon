package Facade;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 19:56
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 正常情况下我们要画一个圆形，一个长方形，一个三角形
         * 是这样的
         */
        Shape shape = new CircleShape();
        shape.draw();
        shape = new RectangleShape();
        shape.draw();
        shape = new TriangleShape();
        shape.draw();

        /**
         * 门面模式下调用是这样的
         */
        Facade facade = new Facade();
        facade.drawCircle();
        facade.drawRectangle();
        facade.drawTriangle();
    }
}
