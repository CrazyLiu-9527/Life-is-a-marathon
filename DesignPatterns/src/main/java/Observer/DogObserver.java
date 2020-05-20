package Observer;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 10:21
 */
public class DogObserver implements Observer {
    @Override
    public void actionWakeUp(Event event) {
        wang();
    }
    public void wang() {
        System.out.println("dog wang ...");
    }
}
