package refactor.case2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import refactor.case2.constant.User;
import refactor.case2.dto.UserInputDto;
import refactor.case2.service.UserServiceImpl;
import refactor.case2.convert.UserInputDtoConverter;

/**
 * 解决当转化操作有很多种的情况的时候，进行统一的操作，定义一个抽象接口
 *
 * @author zhiyuanliu
 * @date 2020/6/30 11:21
 */
@RestController
public class ApiController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public User addUser(UserInputDto userInputDTO) {
        // 1 new一个对象来转化是没有必要的，而且每一个转化对象都是由在遇到 DTO 转化的时候才会出现，那我们应该考虑一下，是否可以将这个类和 DTO 进行聚合呢
        User user = new UserInputDtoConverter().convert(userInputDTO);

        // 2 我们在将类和dto聚合之后，可以直接调用convert方法
        user = userInputDTO.convert();

        return userService.addUser(user);
    }


}
