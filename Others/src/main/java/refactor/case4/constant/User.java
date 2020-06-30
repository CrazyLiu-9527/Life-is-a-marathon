package refactor.case4.constant;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 11:27
 */
@Setter
@Getter
//@Accessors(chain = true)
@Builder
public class User {
    private String username;
    private int age;
    private String nickname;
}
