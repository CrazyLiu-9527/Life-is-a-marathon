package per.lzy.springlearning.beaninjection.model.contract;

import com.google.common.base.Throwables;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;
import per.lzy.springlearning.beaninjection.commons.exception.BusinessException;

/**
 * 接口错误信息
 *
 * @author zhiyuanliu
 * @date 2020/6/30 17:19
 */
@Setter
@Getter
@NoArgsConstructor
public class ErrorDataType {
    private String message;
    private long errorCode;
    private String stackTrace;

    public ErrorDataType(Exception e) {
        this.message = e.getMessage();
        if (e.getClass().getSuperclass().getName().equals(BusinessException.class.getName())) {
            this.errorCode = ((BusinessException) e).getExceptionCode();
        } else {
            this.errorCode = 10000L;
        }

        String trackerror = System.getProperty("service.trackerror");
        boolean isTrack = !StringUtils.hasLength(trackerror) && trackerror.toLowerCase().equals("true");
        if (e.getStackTrace() != null && isTrack) {
            this.stackTrace = Throwables.getStackTraceAsString(e);
        }

    }
}
