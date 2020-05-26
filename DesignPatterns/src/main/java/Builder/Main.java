package Builder;

/**
 * @author zhiyuanliu
 * @date 2020/5/26 15:27
 */
public class Main {
    public static void main(String[] args) {
        //自己编写
        User user = User.builder()
                .name("小明")
                .age(18)
                .nickName("明明")
                .password("2324432")
                .build();

        //lombok自动生成
        User2 user2 = User2.builder()
                .name("小红")
                .age(18)
                .nickName("红红")
                .password("131313")
                .build();
    }

}
