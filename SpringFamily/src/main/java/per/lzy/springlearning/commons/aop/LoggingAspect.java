package per.lzy.springlearning.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 定义切面
 * @author zhiyuanliu
 * @date 2020/7/7 19:25
 */
@Aspect
@Component
public class LoggingAspect {
    /**
     * 我们在使用AOP时，要注意到虽然Spring容器可以把指定的方法通过AOP规则装配到指定的Bean的指定方法前后，
     * 但是，如果自动装配时，因为不恰当的范围，容易导致意想不到的结果，即很多不需要AOP代理的Bean也被自动代理了，
     * 并且，后续新增的Bean，如果不清楚现有的AOP装配规则，容易被强迫装配。
     *
     * 使用注解+aspect是一种更好的解决方案，只需要在方法上标注自定义的注解就可以实现监控
     */

    // 在每个方法前执行:
    // 注释掉查看自定义注解的效果
//    @Before("execution(public * per.lzy.springlearning.service.impl.*.*(..))")
    public void doAccessCheck() {
        System.err.println("[Before] do access check...");
    }

    // 在每个方法前后执行:
    // 注释掉查看自定义注解的效果
//    @Around("execution(public * per.lzy.springlearning.service.impl.*.*(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        System.err.println("[Around] start " + pjp.getSignature());
        Object retVal = pjp.proceed();
        System.err.println("[Around] done " + pjp.getSignature());
        return retVal;
    }
}
