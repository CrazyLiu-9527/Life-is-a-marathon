package refactor.case4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import refactor.case4.constant.User;
import refactor.case4.dto.UserInputDto;
import refactor.case4.service.UserServiceImpl;

/**
 * 简化代码，使用lombok
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
        User user = userInputDTO.convertToUser();

        // 使用链式调用生成user类 注解 @Accessors(chain = true)
//        user = new User()
//                .setAge(11)
//                .setNickname("小明")
//                .setUsername("明明");
        // 使用链式调用生成user类 注解 @Builder 建造者模式
        user = User.builder()
                .age(11)
                .username("小明")
                .nickname("明明")
                .build();

        return userService.addUser(user);
    }


}
