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
## spring-boot rabbitmq简单集成

## 配置
queue - exchange - routingKey
```xml
    <!--声明exchange交换机并绑定queue,key 为 routingKey-->
    <rabbit:direct-exchange name="exchange_name_2" durable="true" auto-delete="false" id="exchange_name_2">
        <rabbit:bindings>
            <rabbit:binding queue="queue_name_1" key="key1"/>
            <rabbit:binding queue="queue_name_2" key="key1"/>
        </rabbit:bindings>
    </rabbit:direct-exchange>
```
exchange类型和routingKey匹配关系。

应答机制

持久化配置

错误重试机制

## 参考
1. https://github.com/401Studio/WeekLearn/issues/2
2. http://www.ityouknow.com/springboot/2016/11/30/springboot(%E5%85%AB)-RabbitMQ%E8%AF%A6%E8%A7%A3.html