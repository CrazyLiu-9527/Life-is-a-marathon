package AbstractFactory;

/**
 * @author zhiyuanliu
 * @date 2020/5/19 19:31
 */
public class HuaWeiHeadSet implements HeadSet {
    @Override
    public void listen() {
        System.out.println("HuaWeiPhone listening...");
    }
}
