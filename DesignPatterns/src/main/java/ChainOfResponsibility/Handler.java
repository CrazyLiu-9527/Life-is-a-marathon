package ChainOfResponsibility;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 14:36
 */
abstract class Handler {
    protected Handler handler;

    /**
     * 责任链模式的核心：当前节点需要持有一个后继者
     */
    public Handler setNextHandler(Handler handler) {
        this.handler = handler;
        return handler;
    }

    /**
     * 具体处理流程
     *
     * @param event
     */
    public abstract void process(Event event);
}
