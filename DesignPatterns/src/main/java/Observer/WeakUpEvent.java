package Observer;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 10:19
 */
public class WeakUpEvent extends Event<Child> {

    long timestamp;
    String loc;
    Child source;

    public WeakUpEvent(long timestamp, String loc, Child source) {
        this.timestamp = timestamp;
        this.loc = loc;
        this.source = source;
    }

    @Override
    Child getSource() {
        return source;
    }
}
