package refactor.case2.dto;

import refactor.case2.constant.User;
import refactor.case2.convert.UserInputDtoConverter;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 11:27
 */
public class UserInputDto {
    private String username;
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User convert() {
        UserInputDtoConverter converter = new UserInputDtoConverter();
        return converter.convert(this);
    }
}
