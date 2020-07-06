package per.lzy.springlearning.beaninjection.commons.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 *
 * @author zhiyuanliu
 * @date 2020/6/30 17:21
 */
@Setter
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 8742468291242069519L;

    private long exceptionCode;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(Throwable throwable, String frdMessage) {
        super(throwable);
    }
}
