### 1. 循环依赖是什么？

Bean A 依赖 B，Bean B 依赖 A这种情况下出现循环依赖。
Bean A → Bean B → Bean A
更复杂的间接依赖造成的循环依赖如下。
Bean A → Bean B → Bean C → Bean D → Bean E → Bean A

### 2. 循环依赖会产生什么结果？

当Spring正在加载所有Bean时，Spring尝试以能正常创建Bean的顺序去创建Bean。
例如，有如下依赖:
Bean A → Bean B → Bean C
Spring先创建beanC，接着创建bean B（将C注入B中)，最后创建bean A(将B注入A中)。

但当存在循环依赖时，Spring将无法决定先创建哪个bean。这种情况下，Spring将产生异常BeanCurrentlyInCreationException。

当使用构造器注入时经常会发生循环依赖问题。如果使用其它类型的注入方式能够避免这种问题。

### 3. 构造器注入循环依赖实例

首先定义两个相互通过构造器注入依赖的bean。

```java
@Component
public class CircularDependencyA {
 
    private CircularDependencyB circB;
 
    @Autowired
    public CircularDependencyA(CircularDependencyB circB) {
        this.circB = circB;
    }
}

@Component
public class CircularDependencyB {
 
    private CircularDependencyA circA;
 
    @Autowired
    public CircularDependencyB(CircularDependencyA circA) {
        this.circA = circA;
    }
}

@Configuration
@ComponentScan(basePackages = { "com.baeldung.circulardependency" })
public class TestConfig {
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class CircularDependencyTest {
 
    @Test
    public void givenCircularDependency_whenConstructorInjection_thenItFails() {
        // Empty test; we just want the context to load
    }
}

```

运行方法givenCircularDependency_whenConstructorInjection_thenItFails将会产生异常：**BeanCurrentlyInCreationException: Error creating bean with name ‘circularDependencyA’:
Requested bean is currently in creation: Is there an unresolvable circular reference?**

### 4.解决方法

处理这种问题目前有如下几种常见方式。

#### 4.1 重新设计

重新设计结构，消除循环依赖。

#### 4.2 使用注解 @Lazy

一种最简单的消除循环依赖的方式是通过延迟加载。在注入依赖时，先注入代理对象，当首次使用时再创建对象完成注入。

```java
@Component
public class CircularDependencyA {
 
    private CircularDependencyB circB;
 
    @Autowired
    public CircularDependencyA(@Lazy CircularDependencyB circB) {
        this.circB = circB;
    }
}

```

使用@Lazy后，运行代码，可以看到异常消除。

#### 4.3 使用Setter/Field注入

Spring文档建议的一种方式是使用setter注入。当依赖最终被使用时才进行注入。对前文的样例代码少做修改，来观察测试效果。

```java
@Component
public class CircularDependencyA {
 
    private CircularDependencyB circB;
 
    @Autowired
    public void setCircB(CircularDependencyB circB) {
        this.circB = circB;
    }
 
    public CircularDependencyB getCircB() {
        return circB;
    }
}

@Component
public class CircularDependencyB {
 
    private CircularDependencyA circA;
 
    private String message = "Hi!";
 
    @Autowired
    public void setCircA(CircularDependencyA circA) {
        this.circA = circA;
    }
 
    public String getMessage() {
        return message;
    }
}

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class CircularDependencyTest {
 
    @Autowired
    ApplicationContext context;
 
    @Bean
    public CircularDependencyA getCircularDependencyA() {
        return new CircularDependencyA();
    }
 
    @Bean
    public CircularDependencyB getCircularDependencyB() {
        return new CircularDependencyB();
    }
 
    @Test
    public void givenCircularDependency_whenSetterInjection_thenItWorks() {
        CircularDependencyA circA = context.getBean(CircularDependencyA.class);

        Assert.assertEquals("Hi!", circA.getCircB().getMessage());
    }
}

```

#### 4.4 使用@PostConstruct

```java
@Component
public class CircularDependencyA {
 
    @Autowired
    private CircularDependencyB circB;
 
    @PostConstruct
    public void init() {
        circB.setCircA(this);
    }
 
    public CircularDependencyB getCircB() {
        return circB;
    }
}

@Component
public class CircularDependencyB {
 
    private CircularDependencyA circA;
     
    private String message = "Hi!";
 
    public void setCircA(CircularDependencyA circA) {
        this.circA = circA;
    }
     
    public String getMessage() {
        return message;
    }
}
```

#### 4.5 实现ApplicationContextAware与InitializingBean

```java
@Component
public class CircularDependencyA implements ApplicationContextAware, InitializingBean {
 
    private CircularDependencyB circB;
 
    private ApplicationContext context;
 
    public CircularDependencyB getCircB() {
        return circB;
    }
 
    @Override
    public void afterPropertiesSet() throws Exception {
        circB = context.getBean(CircularDependencyB.class);
    }
 
    @Override
    public void setApplicationContext(final ApplicationContext ctx) 
        throws BeansException {
        context = ctx;
    }
}

@Component
public class CircularDependencyB {
 
    private CircularDependencyA circA;
 
    private String message = "Hi!";
 
    @Autowired
    public void setCircA(CircularDependencyA circA) {
        this.circA = circA;
    }
 
    public String getMessage() {
        return message;
    }
}

```



### 5.spring是如何解决循环依赖的？

处理循环依赖有多种方式。首先考虑是否能够通过重新设计依赖来避免循环依赖。如果确实需要循环依赖，那么可以通过前文提到的方式来处理。优先建议使用setter注入来解决。

> 循环依赖就是N个类中循环嵌套引用，如果在日常开发中我们用new 对象的方式发生这种循环依赖的话程序会在运行时一直循环调用，直至内存溢出报错。下面说一下Spring是如果解决的。

首先，需要明确的是spring对循环依赖的处理有三种情况： ①构造器的循环依赖：这种依赖spring是处理不了的，直 接抛出BeanCurrentlylnCreationException异常。 ②单例模式下的setter循环依赖：通过“三级缓存”处理循环依赖。 ③非单例循环依赖：无法处理。

spring单例对象的初始化大略分为三步：

1. createBeanInstance：实例化，其实也就是调用对象的构造方法实例化对象
2. populateBean：填充属性，这一步主要是多bean的依赖属性进行填充
3. initializeBean：调用spring xml中的init 方法。

从上面讲述的单例bean初始化步骤我们可以知道，循环依赖主要发生在第一、第二步。也就是构造器循环依赖和field循环依赖。 接下来，我们具体看看spring是如何处理三种循环依赖的。

#### 1、构造器循环依赖

this .singletonsCurrentlylnCreation.add(beanName）将当前正要创建的bean 记录在缓存中 Spring 容器将每一个正在创建的bean 标识符放在一个“当前创建bean 池”中， bean 标识：在创建过程中将一直保持在这个池中，因此如果在创建bean 过程中发现自己已经在“当前 创建bean 池” 里时，将抛出BeanCurrentlylnCreationException 异常表示循环依赖；而对于创建 完毕的bean 将从“ 当前创建bean 池”中清除掉。

#### 2、setter循环依赖

Spring为了解决单例的循环依赖问题，使用了三级缓存。

```java
/** Cache of singleton objects: bean name –> bean instance */
private final Map singletonObjects = new ConcurrentHashMap(256);
/** Cache of singleton factories: bean name –> ObjectFactory */
private final Map> singletonFactories = new HashMap>(16);
/** Cache of early singleton objects: bean name –> bean instance */
private final Map earlySingletonObjects = new HashMap(16);
```

这三级缓存的作用分别是：

singletonFactories ： 进入实例化阶段的单例对象工厂的cache （三级缓存）

earlySingletonObjects ：完成实例化但是尚未初始化的，提前暴光的单例对象的Cache （二级缓存）

singletonObjects：完成初始化的单例对象的cache（一级缓存）

我们在创建bean的时候，会首先从cache中获取这个bean，这个缓存就是sigletonObjects。主要的调用方法是：

```java
protected Object getSingleton(String beanName, boolean allowEarlyReference) {
    Object singletonObject = this.singletonObjects.get(beanName);
    //isSingletonCurrentlyInCreation()判断当前单例bean是否正在创建中
    if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
        synchronized (this.singletonObjects) {
            singletonObject = this.earlySingletonObjects.get(beanName);
            //allowEarlyReference 是否允许从singletonFactories中通过getObject拿到对象
            if (singletonObject == null && allowEarlyReference) {
                ObjectFactory<?> singletonFactory = this.singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    //从singletonFactories中移除，并放入earlySingletonObjects中。
                    //其实也就是从三级缓存移动到了二级缓存
                    this.earlySingletonObjects.put(beanName, singletonObject);
                    this.singletonFactories.remove(beanName);
                }
            }
        }
    }
    return (singletonObject != NULL_OBJECT ? singletonObject : null);
}
```

从上面三级缓存的分析，我们可以知道，Spring解决循环依赖的诀窍就在于singletonFactories这个三级cache。这个cache的类型是ObjectFactory，定义如下：

```java
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
```

这个接口在AbstractBeanFactory里实现，并在核心方法doCreateBean（）引用下面的方法:

```java
protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
    Assert.notNull(singletonFactory, "Singleton factory must not be null");
    synchronized (this.singletonObjects) {
        if (!this.singletonObjects.containsKey(beanName)) {
            this.singletonFactories.put(beanName, singletonFactory);
            this.earlySingletonObjects.remove(beanName);
            this.registeredSingletons.add(beanName);
        }
    }
}
```

这段代码发生在createBeanInstance之后，populateBean（）之前，也就是说单例对象此时已经被创建出来(调用了构造器)。这个对象已经被生产出来了，此时将这个对象提前曝光出来，让大家使用。

这样做有什么好处呢？让我们来分析一下“A的某个field或者setter依赖了B的实例对象，同时B的某个field或者setter依赖了A的实例对象”这种循环依赖的情况。A首先完成了初始化的第一步，并且将自己提前曝光到singletonFactories中，此时进行初始化的第二步，发现自己依赖对象B，此时就尝试去get(B)，发现B还没有被create，所以走create流程，B在初始化第一步的时候发现自己依赖了对象A，于是尝试get(A)，尝试一级缓存singletonObjects(肯定没有，因为A还没初始化完全)，尝试二级缓存earlySingletonObjects（也没有），尝试三级缓存singletonFactories，由于A通过ObjectFactory将自己提前曝光了，所以B能够通过ObjectFactory.getObject拿到A对象(虽然A还没有初始化完全，但是总比没有好呀)，B拿到A对象后顺利完成了初始化阶段1、2、3，完全初始化之后将自己放入到一级缓存singletonObjects中。此时返回A中，A此时能拿到B的对象顺利完成自己的初始化阶段2、3，最终A也完成了初始化，进去了一级缓存singletonObjects中，而且更加幸运的是，由于B拿到了A的对象引用，所以B现在hold住的A对象完成了初始化。

#### 3、非单例循环依赖

对于“prototype”作用域bean, Spring 容器无法完成依赖注入，因为Spring 容器不进行缓 存“prototype”作用域的bean ，因此无法提前暴露一个创建中的bean 。


