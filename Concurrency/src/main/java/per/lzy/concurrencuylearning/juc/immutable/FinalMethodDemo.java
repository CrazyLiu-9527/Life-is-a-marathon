package per.lzy.concurrencuylearning.juc.immutable;

/**
 * final修饰的方法
 *
 * @author zhiyuanliu
 * @date 2020/8/11 10:23
 */
public class FinalMethodDemo {
    public static void sleep() {

    }

    public void drink() {

    }

    public final void eat() {

    }
}

class SubClass extends FinalMethodDemo {
    public static void sleep() {

    }

//    public final void eat() {
//
//    }

    @Override
    public void drink() {
        super.drink();
        eat();
    }
}
