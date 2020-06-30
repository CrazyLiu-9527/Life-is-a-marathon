package refactor.case1.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import refactor.case1.constant.User;
import refactor.case1.dto.UserInputDto;
import refactor.case1.service.UserServiceImpl;

/**
 * 普通状态请求进来的操作
 * @author zhiyuanliu
 * @date 2020/6/30 11:21
 */
@RestController
public class ApiController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 1、2、3的三种写法是越来越好的
     * @param userInputDTO
     * @return
     */
    @PostMapping
    public User addUser(UserInputDto userInputDTO) {
        User user = new User();
        // 1 使用set给user赋值
        user.setUsername(userInputDTO.getUsername());
        user.setAge(userInputDTO.getAge());

        // 2 当属性很多的时候一个个set很不方便，可以使用beanUtils来拷贝属性，copyProperties使用的是浅拷贝
        BeanUtils.copyProperties(user, userInputDTO);

        // 3 更好的语义写法
        user = convertFor(userInputDTO);

        return userService.addUser(user);
    }

    // 上面过程使用工具类的操作虽然简化了和优化了代码，但是它的语义是有问题的，将转化过程暴露了出来，我们需要提取出一个转化过程
    private User convertFor(UserInputDto userInputDTO) {
        User user = new User();
        BeanUtils.copyProperties(userInputDTO,user);
        return user;
    }
}
