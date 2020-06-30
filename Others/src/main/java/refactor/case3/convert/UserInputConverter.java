package refactor.case3.convert;

import org.springframework.beans.BeanUtils;
import refactor.case3.constant.User;
import refactor.case3.dto.UserInputDto;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 12:17
 */
public class UserInputConverter implements Converter<UserInputDto, User> {

    @Override
    public User doForward(UserInputDto userInputDto) {
        User user = new User();
        BeanUtils.copyProperties(user, userInputDto);
        return user;
    }

    @Override
    public UserInputDto doBackward(User user) {
        UserInputDto userInputDto = new UserInputDto();
        BeanUtils.copyProperties(userInputDto, user);
        return userInputDto;

        // 当不希望有逆向转化方法的时候，可以直接抛出一个异常
//        throw new AssertionError("不支持逆向转化方法");
    }
}
