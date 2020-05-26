package Bridge;

/**
 * @author zhiyuanliu
 * @date 2020/5/26 17:02
 */
public abstract class Shape {
    protected DrawApi drawApi;

    protected Shape(DrawApi drawApi){
        this.drawApi = drawApi;
    }
    public abstract void draw();

}
