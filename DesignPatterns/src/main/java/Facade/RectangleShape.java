package Facade;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 19:54
 */
public class RectangleShape implements Shape {
    @Override
    public void draw() {
        System.out.println("画一个长方形...");
    }
}
