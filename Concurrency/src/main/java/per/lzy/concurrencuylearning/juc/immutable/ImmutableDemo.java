package per.lzy.concurrencuylearning.juc.immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 一个属性是对象，但是整体不可变，其他类无法修改set里面的数据
 * 这样的类是线程安全的
 *
 * @author zhiyuanliu
 * @date 2020/8/11 10:56
 */
public class ImmutableDemo {
    private final Set<String> students = new HashSet<>();

    public ImmutableDemo() {
        students.add("小明");
        students.add("小强");
        students.add("小红");
    }

    public boolean contains(String name) {
        return students.contains(name);
    }
}
