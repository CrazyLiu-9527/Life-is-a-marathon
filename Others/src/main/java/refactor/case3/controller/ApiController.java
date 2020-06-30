package refactor.case3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import refactor.case3.constant.User;
import refactor.case3.dto.UserInputDto;
import refactor.case3.service.UserServiceImpl;

/**
 * 为bean添加双向的转化方法, 见接口和dto类
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

        return userService.addUser(user);
    }


}
