package per.lzy.springlearning.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuanliu
 * @date 2020/7/7 20:38
 */
@Aspect
@Component
public class MyAnnotationAspect {

    @Around("@annotation(MyAspectPoint)")
    public Object doTest(ProceedingJoinPoint pjp) throws Throwable {
        System.err.println("[Around] start " + pjp.getSignature());
        Object retVal = pjp.proceed();
        System.err.println("[Around] done " + pjp.getSignature());
        return retVal;
    }
}
