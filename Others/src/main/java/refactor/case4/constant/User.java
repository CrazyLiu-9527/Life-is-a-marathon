package refactor.case4.constant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private int age;
    private String nickname;
}
