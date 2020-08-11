package per.lzy.concurrencuylearning.juc.immutable;

/**
 * 不可变的对象，演示其他类无法修改这个对象，public也不行
 *
 * @author zhiyuanliu
 * @date 2020/8/11 10:16
 */
public class Person {
    final int age = 18;
    final TestFinal testFinal = new TestFinal();
    String alice = new String("alice");
    final String name = alice;

    public static void main(String[] args) {
        Person person = new Person();
        person.alice = "44";
        System.out.println(person.name);
    }
}
