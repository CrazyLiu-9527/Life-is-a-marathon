package per.lzy.springlearning.beaninjection.model.contract;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import per.lzy.springlearning.beaninjection.model.enums.EnumAckCodeType;

import java.io.Serializable;

/**
 * response信息
 * @author zhiyuanliu
 * @date 2020/6/30 17:08
 */
@Setter
@Getter
@NoArgsConstructor
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -8165256215699424007L;

    private ResponseStatusType responseStatusType;

    public ResponseStatusType getResponseStatusType() {
        return this.responseStatusType;
    }

    public void setResponseStatusType(ResponseStatusType responseStatusType) {
        this.responseStatusType = responseStatusType;
    }

    public void setSuccess() {
        this.responseStatusType = new ResponseStatusType();
        this.responseStatusType.setAck(EnumAckCodeType.SUCCESS);
    }

    public void setFailure() {
        this.responseStatusType = new ResponseStatusType();
        this.responseStatusType.setAck(EnumAckCodeType.FAILURE);
    }
}
