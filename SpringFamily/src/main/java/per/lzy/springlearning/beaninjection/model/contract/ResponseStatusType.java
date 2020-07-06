package per.lzy.springlearning.beaninjection.model.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import per.lzy.springlearning.beaninjection.model.enums.EnumAckCodeType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * response类型
 *
 * @author zhiyuanliu
 * @date 2020/6/30 17:15
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatusType {
    private Calendar timestamp;
    private EnumAckCodeType ack;
    private String build;
    private String version;
    private List<ErrorDataType> errors;
    private List<ExtensionType> extensions;

    public ResponseStatusType(Exception e) {
        this.init();
        this.errors = new ArrayList<>();
        ErrorDataType errorDataType = new ErrorDataType(e);
        this.errors.add(errorDataType);
        this.ack = EnumAckCodeType.FAILURE;
    }

    private void init() {
        this.timestamp = Calendar.getInstance();
        this.build = System.getProperty("service.build") == null ? "000000" : System.getProperty("service.build");
        this.version = System.getProperty("service.version") == null ? "0.0.0.0" : System.getProperty("service.version");
    }
}
