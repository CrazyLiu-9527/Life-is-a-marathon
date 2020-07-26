package per.lzy.concurrencuylearning.core.deadlock.dynamicsequentialdeadlocks;

/**
 * @author zhiyuanliu
 * @date 2020/7/16 12:11
 */
public class UnsafeTransfer implements ITransfer {
    /**
     * 转账
     *
     * @param from   转出账户
     * @param to     转入账户
     * @param amount 转账金额
     * @throws InterruptedException
     */
    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName() + " get " + from.getName());
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " to " + to.getName());
                from.flyMoney(amount);
                to.addMoney(amount);
                System.out.println(from);
                System.out.println(to);
            }
        }
    }
}
