package Command;

/**
 * @author liuzy
 * @date 2020/5/27 00:28
 */
public abstract class Command {
    public abstract void doit(); //exec run

    public abstract void undo();
}
