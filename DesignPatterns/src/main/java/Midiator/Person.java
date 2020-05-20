package Midiator;

/**
 * @author zhiyuanliu
 * @date 2020/5/20 21:09
 */
public class Person {
    private String name;
    private Mediator mediator;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public Person(String name) {
        this.name = name;
    }

    public void send(String message) {
        mediator.send(this.name, message);
    }

    public void receive(String message) {
        System.out.println(name + "收到消息：" + message);
    }
}
