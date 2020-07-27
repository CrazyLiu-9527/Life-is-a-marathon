package per.lzy.concurrencuylearning.practice.deadlock.dynamicsequentialdeadlocks;

/**
 * 转账动作接口
 *
 * @author zhiyuanliu
 * @date 2020/7/16 12:10
 */
public interface ITransfer {
    /**
     * 转账
     *
     * @param from   转出账户
     * @param to     转入账户
     * @param amount 转账金额
     * @throws InterruptedException
     */
    void transfer(UserAccount from, UserAccount to, int amount)
            throws InterruptedException;
}
