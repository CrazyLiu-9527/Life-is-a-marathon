package per.lzy.springlearning.commons;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;

/**
 * @author zhiyuanliu
 * @date 2020/7/6 22:25
 */
@ComponentScan
@Configuration
public class AnimalConfig {

    // 创建第三方bean Spring对标记为@Bean的方法只调用一次，因此返回的Bean仍然是单例。
    @Bean
    public ZoneId createZoneId() {
        return ZoneId.of("Z");
    }
}
