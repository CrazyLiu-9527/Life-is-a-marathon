package per.lzy.concurrencuylearning.juc.collections.predecessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 演示Collections.synchronizedList(new ArrayList<E>())
 *
 * @author zhiyuanliu
 * @date 2020/8/11 14:57
 */
public class SynList {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        list.add(5);
        System.out.println(list.get(0));

        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
    }
}
