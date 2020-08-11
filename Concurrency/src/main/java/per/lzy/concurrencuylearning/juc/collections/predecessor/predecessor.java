package per.lzy.concurrencuylearning.juc.collections.predecessor;

import java.util.Hashtable;

/**
 * @author zhiyuanliu
 * @date 2020/8/11 14:54
 */
public class predecessor {
    public static void main(String[] args) {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("涨幅", "80%");
        System.out.println(hashtable.get("涨幅"));
    }
}
