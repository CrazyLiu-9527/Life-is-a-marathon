package ChainOfResponsibility;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 14:46
 */
public class PresidentHandler extends Handler {
    /**
     * 具体处理流程
     *
     * @param event
     */
    @Override
    public void process(Event event) {
        System.out.println(event.getName() + "请假" + event.getDays() + "天，总经理处理完成");
    }
}
