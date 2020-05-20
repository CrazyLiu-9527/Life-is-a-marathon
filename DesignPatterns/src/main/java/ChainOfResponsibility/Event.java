package ChainOfResponsibility;

/**
 * 传递的事件对象
 * @author zhiyuanliu
 * @date 2020/5/20 14:35
 */
public class Event {
    private String name;
    private int days;

    public Event(String name, int days) {
        this.name = name;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public int getDays() {
        return days;
    }
}
