package State.v2;

/**
 * @author liuzy
 * @date 2020/5/27 00:15
 */
public class MM {
    String name;
    MMState state = new MMHappyState();

    public void smile() {
        state.smile();
    }

    public void cry() {
        state.cry();
    }

    public void say() {
        state.say();
    }
}
