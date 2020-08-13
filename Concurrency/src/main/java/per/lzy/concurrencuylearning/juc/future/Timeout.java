package per.lzy.concurrencuylearning.juc.future;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 演示get的超时方法，需要注意超时后处理，调用future.cancel()。
 * 演示cancel传入true和false的区别，代表是否中断正在执行的任务。
 *
 * @author zhiyuanliu
 * @date 2020/8/13 15:13
 */
public class Timeout {
    public static final Ad ad = new Ad("无网络的时候默认广告");
    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        Timeout timeout = new Timeout();
        timeout.printAd();
    }

    public void printAd() {
        Future<Ad> future = service.submit(new FetchAdTask());

        Ad ad;
        try {
            ad = future.get(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时候的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = future.cancel(true);
            System.out.println("cancel的结果：" + cancel);
        }
        service.shutdown();
        System.out.println(ad);
    }

    @Data
    @AllArgsConstructor
    static class Ad {
        private String name;
    }

    static class FetchAdTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("sleep期间被中断了");
                return new Ad("被中断的时候默认广告");
            }
            return new Ad("挖掘机技术哪家强，中国山东找蓝翔");
        }
    }
}
