package per.lzy.springlearning.commons.aop;

import per.lzy.springlearning.service.impl.CatAnimalServiceImpl;

/**
 * 动态代理实际上就是创建了一个代理类继承于被代理类，其中维护了被代理类和切面两个对象的引用
 * 并重写了被代理类的对象，使系统在调用的时候调用代理类的方法。代理类在重写方法的前后可以加入不同的逻辑，实现横切的效果
 * @author zhiyuanliu
 * @date 2020/7/7 20:07
 */
public class CatAnimalServiceImplProxy extends CatAnimalServiceImpl {
    private CatAnimalServiceImpl target;
    private LoggingAspect aspect;

    public CatAnimalServiceImplProxy(CatAnimalServiceImpl target, LoggingAspect aspect) {
        this.target = target;
        this.aspect = aspect;
    }

    @Override
    public void run() {
        // 先执行Aspect的代码:
        aspect.doAccessCheck();
        // 再执行UserService的逻辑:
        target.run();
    }

    @Override
    public void eat() {
        // 先执行Aspect的代码:
        aspect.doAccessCheck();
        // 再执行UserService的逻辑:
        target.eat();
    }
}
