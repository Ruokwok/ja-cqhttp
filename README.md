# 什么是ja-cqhttp?

ja-cqhttp是统一的聊天机器人应用接口标准[OneBot-v11](https://11.onebot.dev/)的Java实现，可以通过ja-cqhttp快速构建Java平台的聊天机器人程序。

本项目基于[go-cqhttp](https://github.com/Mrs4s/go-cqhttp)的API开发，兼容 OneBot-v11 绝大多数内容，并在其基础上做了一些扩展

 - 此文档正在完善中

# 开始使用

## 使用

目前支持实现端正向/反向WebSocket连接，通过`OneBot`对象操作机器人，一个`OneBot`对象对应一条与实现端的连接。

### 客户端模式 (用于实现端正向WebSocket服务器)

```java
long groupID = 100000000;   //群号
OneBotClient bot = new OneBotClient("ws://127.0.0.1:6700/");
// bot.run();       //同步运行
bot.runAsync(3000); //异步运行
bot.sendGroupMessage(groupID, "你好吗?" + Face.HUAJI);    //发送一条群消息
```

- 由于使用同一条WebSocket连接监听事件和操作API，所以连接地址的路径应为`/`而非`/event`、`/api`。
- `bot.run()`方法为同步运行，此时该方法会被阻塞。
- `bot.runAsync(long time)`方法为异步运行，支持断线自动重连，参数为断线重连的等待时间，此方法返回一个运行OneBot的线程。

### 服务端模式 (用于实现端反向WebSocket服务器)

```java
long qq = 1000000000;   //登录机器人的QQ号
int port = 6700;        //服务端监听端口
OneBotServer server = new OneBotServer(port);
//server.start();       //同步启动
server.startAsync();    //异步启动

//等待实现端连接后，可以使用以下方法获取OneBot实例
OneBot bot = OneBot.getActiveInstance(qq);
bot.sendGroupMessage(groupID, "你好吗?" + Face.HUAJI);    //发送一条群消息
server.stop();          //关闭OneBotServer服务器
```

- `server.start()`方法为同步启动，此时该方法会被阻塞。
- `server.startAsync()`方法为异步启动，此方法返回一个运行OneBotServer的线程。
- 由于服务端无法主动重连，每次断线重连后OneBot实例会被更新，所以不应保存OneBot对象多次使用，每次使用时请通过OneBot.getActiveInstance(long qq)获取最新的实例。
