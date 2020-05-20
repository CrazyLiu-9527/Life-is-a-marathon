package Observer;

/**
 * Observer
 * @author zhiyuanliu
 * @date 2020/5/20 10:19
 */
public interface Observer {
    /**
     * 对孩子醒来这个事件做出反应
     * @param event
     */
    void actionWakeUp(Event event);
}
