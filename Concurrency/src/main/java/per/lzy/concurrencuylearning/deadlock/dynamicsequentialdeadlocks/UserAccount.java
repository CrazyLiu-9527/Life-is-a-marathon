package per.lzy.concurrencuylearning.deadlock.dynamicsequentialdeadlocks;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 个人账户实体
 *
 * @author zhiyuanliu
 * @date 2020/7/16 11:33
 */
@Data
@AllArgsConstructor
public class UserAccount {
    private final Lock lock = new ReentrantLock();
    /**
     * 账户名称
     */
    private String name;
    /**
     * 账户余额
     */
    private int money;

    /**
     * 转入资金
     *
     * @param amount
     */
    public void addMoney(int amount) {
        this.money = this.money + amount;
    }

    /**
     * 转出资金
     *
     * @param amount
     */
    public void flyMoney(int amount) {
        this.money = this.money + amount;
    }
}
