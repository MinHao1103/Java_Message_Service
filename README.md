# 使用版本
* Java 版本：8
* Spring Boot 版本：2.7.10
* ActiveMQ 版本：5.16.0

# ActiveMQ 依賴套件
* 非 SpringBoot 框架
```xml
<dependency>
      <groupId>org.apache.activemq</groupId>
      <artifactId>activemq-all</artifactId>
      <version>5.16.0</version>
</dependency>
```
* SpringBoot 框架
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-activemq</artifactId>
</dependency>
```

# JMS 相關資訊
* [概念介紹](https://chambray-pincushion-612.notion.site/JMS-MQTT-Active-MQ-6fe30a34a6234c44ae1e144d07866bbb?pvs=4)
* [API 介紹](https://chambray-pincushion-612.notion.site/JMS-Java-Message-Service-API-14db27b7ee4042a082c0f4c2644497ce?pvs=4)
* [使用 Oracle VirtualBox 安裝 ubuntu 虛擬機](https://chambray-pincushion-612.notion.site/Oracle-VirtualBox-ubuntu-1c5260b74fd248369e55c26547bad76c?pvs=4)
* [在 Ubuntu 環境安裝 ActiveMQ](https://chambray-pincushion-612.notion.site/ActiveMQ-e24ac236ac154f3dab9396bbb4cb8846?pvs=4)

# 其他備註
* ActiveMQ 5.18.3 只支援 Java11 啟動，程式用 Java8 的版本可以正常發送訊息給 ActiveMQ
* SpringBoot 的 JMS 是用 SpringBootTest 測試
* 新增 [ActiveMQConfig.java](src%2Fmain%2Fjava%2Fcom%2Fjms%2Fjms%2FSpringBoot_JMS%2FActiveMQConfig.java) 檔配置點對點以及發布訂閱模型