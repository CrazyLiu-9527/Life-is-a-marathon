package Command;

/**
 * @author liuzy
 * @date 2020/5/27 00:31
 */
public class InsertCommand extends Command {
    Content c;
    String strToInsert = "http://www.bilibili.com";

    public InsertCommand(Content c) {
        this.c = c;
    }

    @Override
    public void doit() {
        c.msg = c.msg + strToInsert;
    }

    @Override
    public void undo() {
        c.msg = c.msg.substring(0, c.msg.length() - strToInsert.length());
    }
}
