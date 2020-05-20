package Observer;

/**
 * event
 * @author zhiyuanliu
 * @date 2020/5/20 10:18
 */
abstract class Event<T> {
    abstract T getSource();
}
