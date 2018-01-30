# spring-boot rabbitmq 集成
## rabbitmq安装（windows）
1. 安装Erland，下载地址：http://www.erlang.org/downloads
下载exe安装包，安装
2. 安装rabbitmq，下载地址：https://www.rabbitmq.com/download.html
下载exe安装包，安装
3. 安装web插件
进入sbin目录，cmd运行命令`rabbitmq-plugins.bat enable rabbitmq_management`
4. 重启rabbitmq，cmd运行命令`net stop rabbitmq && net start rabbitmq`
5. 访问rabbitmq管理页面：http://localhost:15672
默认帐号密码：guest guest
## 基本概念
queue - exchange - routingKey
发送消息时是针对exchange-routingkey发送，根据exchange的类型，匹配到对应的queue发送。

## spring-boot rabbitmq基本配置
maven：
```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-amqp</artifactId>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-mapper-asl -->
    <dependency>
        <groupId>org.codehaus.jackson</groupId>
        <artifactId>jackson-mapper-asl</artifactId>
        <version>1.9.13</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-core-asl -->
    <dependency>
        <groupId>org.codehaus.jackson</groupId>
        <artifactId>jackson-core-asl</artifactId>
        <version>1.9.13</version>
    </dependency>
```
xml配置：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:rabbit="http://www.springframework.org/schema/rabbit"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
     http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd
    http://www.springframework.org/schema/rabbit
    http://www.springframework.org/schema/rabbit/spring-rabbit-1.0.xsd" >
    <description>rabbitmq 连接服务配置</description>

    <!-- 连接配置 -->
    <context:property-placeholder location="classpath:application.properties" />
    <rabbit:connection-factory id="connectionFactory" host="${mq.host}" username="${mq.username}" password="${mq.password}" port="${mq.port}" virtual-host="${mq.vhost}"/>
    <rabbit:admin connection-factory="connectionFactory"/>

    <!-- spring template配置-->
    <rabbit:template exchange="exchange_name_1" id="amqpTemplate"  connection-factory="connectionFactory" message-converter="jsonMessageConverter"/>
    
    <!--queue配置-->
    <rabbit:queue id="queue_name_1" name="queue_name_1" durable="true" auto-delete="false" exclusive="false" />
    <rabbit:queue id="queue_name_2" name="queue_name_2" durable="true" auto-delete="false" exclusive="false" />
    <rabbit:queue id="queue_name_3" name="queue_name_3" durable="true" auto-delete="false" exclusive="false" />
    
    <!--exchange配置,绑定queue,设置路由key-->
    <rabbit:direct-exchange name="exchange_name_1" durable="true" auto-delete="false" id="exchange_name_1">
        <rabbit:bindings>
            <rabbit:binding queue="queue_name_1" key="queue_name_1"/>
            <rabbit:binding queue="queue_name_2" key="key1"/>
        </rabbit:bindings>
    </rabbit:direct-exchange>

    <!--consumer配置-->
    <bean id="reveiver1" class="com.willkeep.mq.receiver.Receiver3" />
    <bean id="reveiver2" class="com.willkeep.mq.receiver.Receiver4" />
    <bean id="reveiver3" class="com.willkeep.mq.receiver.Receiver2" />

    <!-- queue-cosumer 绑定配置 -->
    <rabbit:listener-container connection-factory="connectionFactory" acknowledge="auto" message-converter="jsonMessageConverter">
        <rabbit:listener queues="queue_name_1" ref="reveiver1" />
        <rabbit:listener queues="queue_name_2" ref="reveiver2" />
        <rabbit:listener queues="queue_name_3" ref="reveiver3" />
    </rabbit:listener-container>
    
    <!-- 序列化配置 -->
    <bean id="jsonMessageConverter" class="org.springframework.amqp.support.converter.Jackson2JsonMessageConverter">
        <property name="createMessageIds" value="true"/>
    </bean>

</beans>
```
配置说明：
queue:
```
    exclusive：排他，该队列仅对首次声明它的连接可见，并在连接断开时自动删除
    auto-delete: 自动删除，如果该队列没有任何订阅的消费者的话，该队列会被自动删除。这种队列适用于临时队列。
    durable: 持久化
```

## exchange类型
exchange类型和routingKey匹配关系。
### direct
direct 类型必须完全匹配才会发送
发送到exchange下routingKey匹配的queue。
`rabbitTemplate.convertAndSend(exchangename, routingKey, context);`
### topic
.用来区分单词，“*”用于匹配一个单词，“#”用于匹配多个单词（可以是零个）
比如：
```xml
    <!-- topic exchange -->
    <rabbit:topic-exchange name="topic_exchange" durable="true" auto-delete="false" id="exchange_name_2">
        <rabbit:bindings>
            <rabbit:binding queue="queue_name_1" pattern="#.a.log.#"/>
            <rabbit:binding queue="queue_name_2" pattern="#.b.log.#"/>
            <rabbit:binding queue="queue_name_3" pattern="#.log.#"/>
        </rabbit:bindings>
    </rabbit:topic-exchange>
```
当发送routingKey为`xxx.a.log.xxx`的信息时，会匹配到`queue_name_1`和`queue_name_3`
### fanout
fanout表示广播，当发送到此类型exchange时，会发送到绑定的所有queue。
```xml
    <!-- fanout exchange -->
    <rabbit:fanout-exchange name="fanout_exchange" durable="true" auto-delete="false" id="exchange_name_2">
        <rabbit:bindings>
            <rabbit:binding queue="queue_name_1"/>
            <rabbit:binding queue="queue_name_2"/>
            <rabbit:binding queue="queue_name_3"/>
        </rabbit:bindings>
    </rabbit:fanout-exchange>
```
## 深入

应答机制？

错误重试机制？

消费分配策略？

## 参考
1. https://github.com/401Studio/WeekLearn/issues/2
2. http://www.ityouknow.com/springboot/2016/11/30/springboot(%E5%85%AB)-RabbitMQ%E8%AF%A6%E8%A7%A3.html