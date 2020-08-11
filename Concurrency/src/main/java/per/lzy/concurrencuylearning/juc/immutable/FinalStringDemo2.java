package per.lzy.concurrencuylearning.juc.immutable;

/**
 * @author zhiyuanliu
 * @date 2020/8/11 10:54
 */
public class FinalStringDemo2 {
    public static void main(String[] args) {
        String a = "wukong2";
        final String b = getDashixiong();
        String c = b + 2;
        System.out.println(a == c);

    }

    /**
     * final的值通过方法获取的时候，还是要运行的时候才能确定值
     *
     * @return
     */
    private static String getDashixiong() {
        return "wukong";
    }
}
