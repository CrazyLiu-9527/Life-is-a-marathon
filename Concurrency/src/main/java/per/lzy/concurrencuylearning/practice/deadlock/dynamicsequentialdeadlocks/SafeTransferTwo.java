package per.lzy.concurrencuylearning.practice.deadlock.dynamicsequentialdeadlocks;

import java.util.Random;

/**
 * @author zhiyuanliu
 * @date 2020/7/17 10:29
 */
public class SafeTransferTwo implements ITransfer {
    /**
     * 转账
     * 不会产生死锁的安全转账方式二：
     * 每一个UserAccount里加一个显式锁，进行循环尝试拿锁，只用同时拿到2个锁，才能执行操作。
     *
     * @param from   转出账户
     * @param to     转入账户
     * @param amount 转账金额
     * @throws InterruptedException
     */
    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
        Random r = new Random();
        while (true) {
            if (from.getLock().tryLock()) {
                System.out.println(Thread.currentThread().getName()
                        + " get" + from.getName());
                try {
                    if (to.getLock().tryLock()) {
                        try {
                            System.out.println(Thread.currentThread().getName()
                                    + " get" + to.getName());
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            System.out.println(from);
                            System.out.println(to);
                            break;
                        } finally {
                            to.getLock().unlock();
                        }
                    }
                } finally {
                    from.getLock().unlock();
                }

            }
//            可以看到，虽然最后完成了互相转账动作，但是尝试拿到2把锁的时间过长。
//            这是因为这2个转账动作总是先尝试拿到自己的锁，然后都拿不到另一把锁，又都释放锁再次尝试。
//            直到因为系统调度恰好有一边同时成功拿到2把锁。这种现象叫做活锁。
//            解决办法：每个线程休眠随机数，错开拿锁的时间。
            //Thread.sleep(r.nextInt(2));
        }
    }
}
