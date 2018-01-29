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