package refactor.case3.convert;

/**
 * 定义converter接口, 双向转化
 * @author zhiyuanliu
 * @date 2020/6/30 12:13
 */
public interface Converter<A, B> {
    B doForward(A a);
    A doBackward(B b);
}
