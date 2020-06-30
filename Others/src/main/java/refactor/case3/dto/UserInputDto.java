package refactor.case3.dto;

import refactor.case3.constant.User;
import refactor.case3.convert.UserInputConverter;

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

    public User convertToUser() {
        UserInputConverter converter = new UserInputConverter();
        return converter.doForward(this);
    }

    public UserInputDto convertFor(User user) {
        UserInputConverter converter = new UserInputConverter();
        return converter.doBackward(user);
    }
}
