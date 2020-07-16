package per.lzy.concurrencuylearning.deadlock.dynamicsequentialdeadlocks;

/**
 * 模拟转账动作
 *
 * @author zhiyuanliu
 * @date 2020/7/16 12:13
 */
public class PayCompany {

    public static void main(String[] args) {
        UserAccount jack = new UserAccount("jack", 200000);
        UserAccount rose = new UserAccount("rose", 200000);

        // 运行中有一定几率出现
        // roseToJack get rose
        // jackToRose get jack
        // 双方都在获取对方持有的锁
        ITransfer transfer = new TransferAccount();

//        ITransfer transfer = new SafeOperate();
//        ITransfer transfer = new SafeOperateToo();

        TransferThread jackToRose = new TransferThread("jackToRose", jack, rose, 2000, transfer);
        TransferThread roseToJack = new TransferThread("roseToJack", rose, jack, 4000, transfer);

        jackToRose.start();
        roseToJack.start();
    }

    /**
     * 执行转账动作的线程
     */
    private static class TransferThread extends Thread {
        private String name;
        private UserAccount from;
        private UserAccount to;
        private int amount;
        private ITransfer transfer;

        public TransferThread(String name, UserAccount from, UserAccount to, int amount, ITransfer iTransfer) {
            this.name = name;
            this.from = from;
            this.to = to;
            this.amount = amount;
            this.transfer = iTransfer;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
            try {
                transfer.transfer(from, to, amount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
