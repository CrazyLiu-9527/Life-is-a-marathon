package Observer;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 10:20
 */
public class DadObserver implements Observer {
    @Override
    public void actionWakeUp(Event event) {
        feed();
    }

    private void feed() {
        System.out.println("dad feed ...");
    }
}
