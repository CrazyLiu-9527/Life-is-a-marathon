package ChainOfResponsibility;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 14:40
 */
public class GroupHandler extends Handler {

    /**
     * 具体处理流程
     *
     * @param event
     */
    @Override
    public void process(Event event) {
        System.out.println(event.getName() + "请假" + event.getDays() + "天，组长处理完成");
        handler.process(event);
    }
}
