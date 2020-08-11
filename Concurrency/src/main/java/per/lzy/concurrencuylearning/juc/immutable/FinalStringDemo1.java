package per.lzy.concurrencuylearning.juc.immutable;

/**
 * 一道小问题
 *
 * @author zhiyuanliu
 * @date 2020/8/11 10:51
 */
public class FinalStringDemo1 {

    /**
     * 由于b是final的，所以在编译的时候已经能够确定其值，c的值也能够在编译的时候确定
     * 而d不是final的，在运行的时候才能确定其值，所以d的值也是在运行的时候才能确定的，是在堆上创建的
     *
     * @param args
     */
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = "wukong";
        String d = "wukong";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));
    }

}
