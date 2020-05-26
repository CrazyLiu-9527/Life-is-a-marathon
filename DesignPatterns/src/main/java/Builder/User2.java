package Builder;

import lombok.Builder;

/**
 * 使用lombok,可以快速的创建建造者模式的写法
 * @author zhiyuanliu
 * @date 2020/5/26 15:30
 */
@Builder
public class User2 {
    private String  name;
    private String password;
    private String nickName;
    private int age;
}
