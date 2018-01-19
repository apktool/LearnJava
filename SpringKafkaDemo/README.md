# 错误记录

> 2018-01-19 21:22:25.444  WARN 3532 --- [ad | producer-1] org.apache.kafka.clients.NetworkClient   : Bootstrap broker 192.168.100.41:9092 disconnected

出现上述错误的原因是`spring-kafka`与集群的`kafka`版本不匹配。修改`spring-kafka`的版本即可。

# 查看kafka版本

```bash
find ./libs/ -name \*kafka_\* | head -1 | grep -o '\kafka[^\n]*'
```

比如`kafka_2.10-0.10.0.2.5.0.0-1245-javadoc.jar`，那么`0.10.0.2.5.0.0`则是kafka的版本号。

而版本对应关系可以查看[spring-kafka](http://projects.spring.io/spring-kafka/)
