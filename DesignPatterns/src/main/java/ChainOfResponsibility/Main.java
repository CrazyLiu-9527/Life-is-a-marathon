package ChainOfResponsibility;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 14:46
 */
public class Main {
    public static void main(String[] args) {
        //创建一个事件
        Event event = new Event("小明", 5);
        //创建初始节点
        GroupHandler groupHandler = new GroupHandler();
        //将处理流程链式化
        groupHandler.setNextHandler(new DepartHandler()).setNextHandler(new PresidentHandler());
        //调用处理方法
        groupHandler.process(event);
    }
}
