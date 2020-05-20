package Observer;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 10:20
 */
public class MumObserver implements Observer {
    @Override
    public void actionWakeUp(Event event) {
        hug();
    }

    public void hug() {
        System.out.println("mum hug ...");
    }
}
