package per.lzy.concurrencuylearning.deadlock.dynamicsequentialdeadlocks;

/**
 * @author zhiyuanliu
 * @date 2020/7/17 10:10
 */
public class SafeTransferOne implements ITransfer {

    private static Object tieLock = new Object();

    /**
     * 转账
     * 不会产生死锁的安全转账方式一：
     * 强制定义顺序，如：
     * 比较2个对象的哈希值，谁小先锁谁。如果一样，先抢第三把锁，拿到第三把锁的才能继续拿第一第二把锁。
     *
     * @param from   转出账户
     * @param to     转入账户
     * @param amount 转账金额
     * @throws InterruptedException
     */
    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if (fromHash < toHash) {
            synchronized (from) {
                System.out.println(Thread.currentThread().getName() + " get " + from.getName());
                Thread.sleep(100);
                synchronized (to) {
                    System.out.println(Thread.currentThread().getName() + " get " + to.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                System.out.println(Thread.currentThread().getName() + " get " + to.getName());
                Thread.sleep(100);
                synchronized (from) {
                    System.out.println(Thread.currentThread().getName() + " get " + from.getName());
                    from.flyMoney(amount);
                    to.addMoney(amount);
                    System.out.println(from);
                    System.out.println(to);
                }
            }
        } else {
            synchronized (tieLock) {
                synchronized (from) {
                    synchronized (to) {
                        from.flyMoney(amount);
                        to.addMoney(amount);
                    }
                }
            }
        }
    }
}
