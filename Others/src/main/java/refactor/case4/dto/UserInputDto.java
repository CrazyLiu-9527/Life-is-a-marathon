package refactor.case4.dto;

import lombok.Getter;
import lombok.Setter;
import refactor.case4.constant.User;
import refactor.case4.convert.UserInputConverter;

import javax.validation.constraints.NotNull;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 11:27
 */
@Setter
@Getter
public class UserInputDto {
    @NotNull(message = "姓名不能为空")
    private String username;
    @NotNull(message = "年龄不能为空")
    private int age;

    public User convertToUser() {
        UserInputConverter converter = new UserInputConverter();
        return converter.doForward(this);
    }

    public UserInputDto convertFor(User user) {
        UserInputConverter converter = new UserInputConverter();
        return converter.doBackward(user);
    }
}
