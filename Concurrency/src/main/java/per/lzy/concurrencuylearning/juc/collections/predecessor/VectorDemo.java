package per.lzy.concurrencuylearning.juc.collections.predecessor;

import java.util.Vector;

/**
 * 演示Vector，主要是看Vector源码
 *
 * @author zhiyuanliu
 * @date 2020/8/11 14:56
 */
public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("test");
        System.out.println(vector.get(0));
    }
}
