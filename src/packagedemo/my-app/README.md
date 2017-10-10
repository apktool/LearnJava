
# maven 创建项目

```shell
mvn archetype:generate -DgroupId=com.mycompany.app
   -DartifactId=my-app
   -DarchetypeArtifactId=maven-archetype-quickstart
   -DinteractiveMode=false
```

# maven 单元测试

```shell
mvn test
```

# maven 生成jar包

```
mvn package
```

# 运行程序

```
java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App
```

# 清理maven生成的其他文件

```shell
mvn clean
```
