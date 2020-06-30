package refactor.case2.convert;

import org.springframework.beans.BeanUtils;
import refactor.case2.constant.User;
import refactor.case2.dto.UserInputDto;

/**
 * @author zhiyuanliu
 * @date 2020/6/30 12:17
 */
public class UserInputDtoConverter implements DtoConverter<UserInputDto, User> {
    @Override
    public User convert(UserInputDto userInputDto) {
        User user = new User();
        BeanUtils.copyProperties(user, userInputDto);
        return user;
    }
}
