## 引言

![图片描述](pic/aHR0cHM6Ly9pbWcubXVrZXdhbmcuY29tLzVlMjNiOGZhMDAwMTc5ZWQ0MDAwMzAwMC5wbmc)

（图片来源：https://medium.com/）

上一小节说了消息队列的使用，那么面试官也会顺着MQ话题展开讨论，所以先来一节关于Kafka的基础知识，

2020年，Kafka 依旧炙手可热，一线大公司即使不用Kafka，但是自研产品也都是基于Kafka，或者完全借鉴Kafka设计思想，理论上来说，如果你还没熟练掌握一个MQ框架，Kafka绝对是不错的选择。

关于历史，如果你感兴趣了解一下，至少知道是哪个公司开源的，Kafka最初于2011年在 LinkedIn 开发，自那时起经历了很多改进，后来捐献给Apache基金，如今发展成为一个完整的平台，采用[Scala](https://en.wikipedia.org/wiki/Scala_(programming_language))和[Java](https://en.wikipedia.org/wiki/Java_(programming_language))开发的开源流处理软件。

Kafka 是我工作多年使用最多的消息中间件 ，特点是拥有巨大吞吐量（数百万/秒），作为当下最流行的分布式，可水平扩展，可容错的“消息系统”。



##  

## 1.面试官：可以简述下Kafka架构中比较重要的关键字吗？比如Partition，Broker，你都是怎么理解的？

**问题分析：**

**Kafka基础知识**考察，因为Kafka出色的性能，在集群结构上也有所不同，一些新的概念设计名字初学者可能搞不懂，构建一个 Kafka cluster 首先需要理解 topics, producers, consumers, and brokers 的概念。

**我：**

关于Kafka我做了一些深入了解，它的设计思路还是很值得借鉴的，这其中有6个比较关键的名字概念，弄懂这几个概念才能更好地了解Kafka的工作机制。

- **Producer**
  消息的生产方，如支付系统确认用户已经支付，支付系统要通知订单系统和物流系统，支付系统就是生产者。

- **Consumer**
  消费的接收方，Producer 的案例中，物流系统就是消费方，前两个都比较简单，我就不多说了。

- **Topic**
  每条发布到MQ集群的消息都有一个类别，这个类别被称为topic，可以理解成一类消息的名字。所有的消息都已topic作为单位进行归类。

- **Partition**
  Kafka 物理上分区的概念，每个 Topic 会分散在一个或多个 Partition。一个 Topic 的数据太大了，就分成小片，Kafka 为分区引入多副本模型，副本之间采用“一个leader多follower”的设计，通过多副本实现故障自动转移，保证可用性。

- **Broker：**
  可以理解成一个服务器的节点，集群包含一个或多个服务器，这种服务器被称为 broker。对应用来说，生产者把消费发出去了，就不管了。消费者慢条斯理地按照自己的速率来消费。这段时间可能有大量消息产生，消费者压力还是在一定范围内。做生产者和消费者之间解耦的就是一个缓存服务broker。

- **Kafka Cluster**

  集群就是 Broker 的集合，多个 Broker 组成一个高可用集群。

**Producer 与 Consumer的关系**
![图片描述](pic/aHR0cHM6Ly9pbWcubXVrZXdhbmcuY29tLzVlMjNiOTI2MDAwMTcwOWQwMjU4MDE4MC5wbmc)

**topic 和 Partition 的关系**

一个 **topic** 可以分别存储到多个 **Partition**，每个 **Partition** 有序的。
![图片描述](pic/aHR0cHM6Ly9pbWcubXVrZXdhbmcuY29tLzVlMjNiOTM4MDAwMTFhODQwNDE2MDI2Ny5wbmc)

到这里面试官并没有打断我… 我就继续了。



###  

### 那我们为什么要选择 Kafka 呢？

这里不再列举同类产品都具有的功能，直接总结干货，Kafka 特有的功能：

1. 相比同类中间件 RabbitMQ or ActiveMQ，Kafka **支持批量拉取**消息，大大增加了Kafka的消息吞吐量。

2. 支持多种发送场景：

   1.发送并忘记。

   2.同步发送 。

   3.异步发送+回调函数。

   3种方式虽然在时间上有所差别，但并不是说时间越快的越好，具体使用哪种方式要看具体的业务场景，比如业务要求消息必须是按顺序发送，可以使用第2种**同步发送**，并且只能在一个partation上。如果业务只关心消息的吞吐量，容许少量消息发送失败，也不关注消息的发送顺序，那么可以使用**发送并忘记**的方式。如果业务需要知道消息发送是否成功，并且对消息的顺序不关心，那么可以用异步+回调的方式来发送消息

3. 分布式可高可扩展。Kafka 集群可以透明的扩展，增加新的服务器进集群。

只说了 Kafka 的优势，那别的同类产品就不好了吗？当然不是，存在即真理，每个产品能生存下来，一定有它自己的优势，比如 RabbitMQ，在吞吐量方面稍逊于 Kafka ，但是他们的出发点不一样，RabbitMQ 支持对消息的可靠的传递，支持事务，不支持批量的操作，技术选型中，选择最适合你的，你最了解熟悉的。

| 分布式              | **高性能**       | **持久性和扩展性** |
| :------------------ | :--------------- | :----------------- |
| 支持多分区          | 高吞吐量         | 数据可持久化       |
| 支持多副本          | 低延迟           | 容错性高           |
| 支持多订阅者        | 高并发           | 支持水平在线扩展   |
| 基于*ZooKeeper*调度 | 时间复杂度为O(1) | 消息分发自动平衡   |

*言多必失，说了一堆 Kafka 相比其他产品有多好多快，终于成功给自己挖了一个坑。（?），顺着我的思路展开了问*



##  

## 2.面试官：那为什么Kafka的吞吐量远高于其他同类中间件？

**问题分析：**

多年经验总结，面试中最吃亏的就是你把你不熟悉的东西写在简历上，还有就是你知道结果，不知其原理，源码没看过，好歹也要知道用了巧妙的设计。

**我：**

Kafka 是一个高吞吐量分布式消息系统，并且提供了持久化。其高性能的有两个重要特点：

1. 利用了磁盘连续读写性能远远高于随机读写的特点，内部采用消息的批量处理，zero-copy机制，数据的存储和获取是本地磁盘顺序批量操作，具有O(1)的复杂度，消息处理的效率很高。

2. 并发，将一个topic拆分多个partition， kafka读写的单位是partition，因此，将一个topic拆分为多个partition可以提高吞吐量。但是，这里有个前提，就是不同partition需要位于不同的磁盘（可以在同一个机器）。如果多个partition位于同一个磁盘，那么意味着有多个进程同时对一个磁盘的多个文件进行读写，使得操作系统会对磁盘读写进行频繁调度，也就是破坏了磁盘读写的连续性。
   在linkedlin的测试中，每台机器就加载了6个磁盘，并且不做raid，就是为了充分利用多磁盘并发读写，又保证每个磁盘连续读写的特性。
   ![图片描述](pic/aHR0cHM6Ly9pbWcubXVrZXdhbmcuY29tLzVlMjNiOTdiMDAwMThmMWYxNjQ3MDUxNS5wbmc)

   同一个topic会被分散到多个分片上，并行处理。



##  

## 深入分析

与其说深入分析不如说是实战，我在2000人的微信群里很多初学者反应，整体给我讲Redis，MQ，理论知识学了一大堆了，面试题也基本会答了，可是我还是不知道怎么写，没有机会实践，到底怎么用呀？我入职了会不会试用期过不了？

处于对各个阶段读者对关怀，王炸我写了个Demo，看完再不会？那就多看几遍。



###  

### Kafka 消息的生产与消费模型Demo

**伪代码：使用KafKa客户端发送一条消息**

```java
public class MqProducer {

    private final Logger LOG = LoggerFactory.getLogger(MqProducer.class);
    
    @Resource
    private Producer payProducer;

    public void sendPayMsg(String msg) {
        try {
            LOG.debug("send msg:{}", msg);
            payProducer.send(msg);//发送出去一条消息。
        } catch (MQException e) {
            LOG.error("mq消息异常 message:{}", msg, e);
        }
    }
}
```

**长什么样子？**

即payProducer.send(msg)里的msg的值：

```java
{"businessType":1,"cityId":10,"ctime":1567426767077,"dataKey":20190902,"logType":1,"phone":"13212341234","uid":12345678,"userType":1,"uuid":"32EA02C86D78863"}
```

无论消息长短，都可以看作一个json串，用 key-value的形式传递信息。

**伪代码：接收一条消息**

```JAVA
public class DemoConsumer {

    /**
     * 注意：服务端对单ip创建相同主题相同队列的消费者实例数有限制，超过100个拒绝创建.
     **/
    private static IConsumerProcessor consumer;

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
		properties.setProperty(ConsumerConstants.SubscribeGroup, "dache.risk.log.queue.v2");

        // 创建topic对应的consumer对象（注意每次build调用会产生一个新的实例）
        consumer = KafkaClient.buildConsumerFactory(properties, "topic.xxx.xxx");

        // 调用recvMessageWithParallel设置listener
        consumer.recvMessageWithParallel(String.class, new IMessageListener() {
            @Override
            public ConsumeStatus recvMessage(Message message, MessagetContext context) {
                //TODO:业务侧的消费逻辑代码
                try {
                    System.out.println("message=[" + message.getBody() + "]  partition=" + message.getParttion());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return ConsumeStatus.CONSUME_SUCCESS;
            }
        });

    }
}
```



## 附录：消息管理工具

如果你们刚刚搭建起Kafka集群，还没有完备的页面管理系统，你不妨了解一下这几款开源工具，给领导展示一下解决问题的能力。

为了简化开发者和服务工程师维护 Kafka 集群的工作，基于页面的管理工具必不可少。

常用 Kafka 开源管理工具：

1. **Kafka Manager** ：由 yahoo 团队开发。使用可参考：https://github.com/yahoo/kafka-manager

![图片描述](pic/aHR0cHM6Ly9pbWcubXVrZXdhbmcuY29tLzVlMjNiOWNlMDAwMTdlNTYyMTI2MTA2Mi5wbmc)
\2. **Kafka Lens**：开源项目，允许开发人员在通过代理传递消息时查看消息，也可以按分区过滤消息。

参考：https://github.com/kafka-lens/kafka-lens

图片来源：Kafka Lens

1. **Kafka Monitor** ：测试和监视Kafka集群，而不需要对应用程序进行任何更改。
   使用参考：https://github.com/linkedin/kafka-monitor



##  

## 总结

Kafka架构关键字：

1. Producer
2. Consumer
3. Topic
4. Partition
5. Broker
6. Kafka Cluster

每一个关键词都值得你深入研究，让面试官看到你的亮点吧。

Kafka的性能为何如此优秀：一句话总结：得益于架构采用分布式并行处理，利用磁盘顺序IO批处理。



##  

## 参考资料

1. [Kafka官网](https://kafka.apache.org/)
2. [Thorough Introduction to Apache Kafka](https://medium.com/hackernoon/thorough-introduction-to-apache-kafka-6fbf2989bbc1)
3. 如果你想系统了解下Kafka，可以推荐一本书《深入理解Kafka：核心设计与实践原理》，微信读书就可以免费阅读。

阅读全文 