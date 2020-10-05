[![Github](http://github-svg-buttons.herokuapp.com/star.svg?user=binarywang&repo=WeComScanToLoginDemo&style=flat&background=1081C1)](https://github.com/zclhit/WeComScanToLoginDemo)
-----------------------

### 本项目基于 `WxJava` 的 Demo 演示程序，基于 `Spring Boot` 构建，实现企业微信扫码登录功能。
更多信息请查阅：https://github.com/Wechat-Group/WxJava


1. 请注意，本demo为简化代码编译时加入了 `lombok` 支持，如果不了解 `lombok` 的话，请先学习下相关知识，比如可以阅读[此文章](https://mp.weixin.qq.com/s/cUc-bUcprycADfNepnSwZQ)；
1. 另外，新手遇到问题，请务必先阅读[【开发文档 Wiki 首页】](https://github.com/Wechat-Group/WxJava/wiki)的常见问题部分，可以少走很多弯路，节省不少时间。
1. 配置：复制 `/src/main/resources/application.yml.template` 或者修改其扩展名生成 `application.yml` 文件，根据自己需要填写相关配置（需要注意的是：yml文件内的属性冒号后面的文字之前需要加空格，可参考已有配置，否则属性会设置不成功）；
2. 主要配置说明如下：（ 注意：如果是要配置通讯录同步的应用，`agentId` 可以随便配置一个，保证跟下面服务器URL地址里的一致即可。）
```
wechat:
  cp:
    corpId: 111 （企业ID 在此页面查看：https://work.weixin.qq.com/wework_admin/frame#profile）
    appConfigs:
      - agentId: 1000001 （某一具体应用的AgentId，如果是要配置通讯录同步的应用，可以随便配置一个）
        secret: 1111（该应用的Secret）
        token: 111 （应用中的 “接受消息” 部分的 “接收消息服务器配置” 里的Token值）
        aesKey: 111 （应用中的 “接受消息” 部分的 “接收消息服务器配置” 里的EncodingAESKey值）
      - agentId: 1000002 （另一个应用，以下同上）
        secret: 1111
        token: 111
        aesKey: 111
```
3. 运行Java程序：`WxCpDemoApplication`；
4. 配置企业微信对应应用中的 `接受消息` 部分的 `接收消息服务器配置` URL地址：`http://{可外网访问的域名}/wx/cp/portal/{xxxxx}` （`xxxx` 要跟 `AgentId` 保持一致，注意 `my-domain` 要跟上面的一致，需要符合微信官方的要求）；
6. 根据自己需要修改各个 `handler` 的实现，加入自己的业务逻辑。

### 微信扫码登录操作时序图
