package Midiator;

/**
 * 抽象出来调停者
 *
 * @author zhiyuanliu
 * @date 2020/5/20 20:46
 */
public interface Mediator {

    void register(Person person);

    void send(String from, String message);
}
