package Observer;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 10:21
 */
public class Main {
    public static void main(String[] args) {
        Child child = new Child();
        child.addObserver(new DadObserver());
        child.addObserver(new MumObserver());
        child.addObserver(new DogObserver());
        child.setCry(true);
    }
}
