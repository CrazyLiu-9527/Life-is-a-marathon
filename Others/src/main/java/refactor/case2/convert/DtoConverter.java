package refactor.case2.convert;

/**
 * 定义converter接口
 * @author zhiyuanliu
 * @date 2020/6/30 12:13
 */
public interface DtoConverter<S, T> {
    T convert(S s);
}
