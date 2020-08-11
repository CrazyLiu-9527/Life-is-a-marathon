package per.lzy.concurrencuylearning.juc.immutable;

/**
 * 测试final能否被修改,final修饰对象的时候，对象内部的状态是可以被修改的
 *
 * @author zhiyuanliu
 * @date 2020/8/11 10:12
 */
public class TestFinal {
    String test;

    public static void main(String[] args) {
        final Person person = new Person();
        person.testFinal.test = "13";
        System.out.println(person.testFinal.test);
        person.testFinal.test = "15";
        System.out.println(person.testFinal.test);

    }
}
