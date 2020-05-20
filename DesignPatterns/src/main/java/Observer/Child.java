package Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * subject
 * @author zhiyuanliu
 * @date 2020/5/20 10:18
 */
public class Child {

    private boolean cry = false;
    private List<Observer> observerList = new ArrayList<Observer>();

    public boolean isCry() {
        return cry;
    }

    /**
     * 修改孩子（主题）状态
     * @param cry
     */
    public void setCry(boolean cry) {
        this.cry = cry;
        if(cry) {
            weakUp();
        }
    }

    /**
     * 用于新增观察则对象
     * @param observer
     */
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    /**
     * 通知观察者们
     */
    private void weakUp() {
        cry = true;
        WeakUpEvent event = new WeakUpEvent(System.currentTimeMillis(), "bed", this);

        for (Observer observer : observerList) {
            observer.actionWakeUp(event);
        }
    }


}
