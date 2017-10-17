# Spring JPA Starter

Spring Boot 启动项目，包含以下功能：

- spring-web
- spring-data-jpa
- postgresql
- HikariCP
- springfox-swagger2
- lombok

## 工程目录结构

- [start-web-functest](start-web-functest)：所有的功能、单元测试统一放到一个子项目里
- [start-web](start-web)：控制器、Web配置、Boot启动类，Web接口层代码
- [start-business](start-business)：业务层代码
- [start-data](start-data)：数据访问层代码
- [start-common](start-common)：一些公共和通用功能

## IDE

- 下载 Jetbrains IDEA: `https://www.jetbrains.com/idea/`
- 安装 Lombok 插件：**Preferences** -> **Plugins** -> **Browse repositories** -> 搜索`Lombok plugin` 安装
    插件地址：`https://plugins.jetbrains.com/plugin/6317-lombok-plugin`
- 使用 IDEA 打开项目目录，并导入为 **Gradle** 工程

## 关于使用 `gradlew` 下载 Gradle 安装包很慢问题。

解决方案：

**创建目录**

```
cd ~/
mkdir -p .gradle/wrapper/dists
```

*Windows*

进行用户主目录，再创建 `.gradle/wrapper/dists` 目录。（注意：可能你需要在 CMD 中创建带`.`字符的目录）

**下载 gradle-3.5.1-bin.tar.gz 包**

```
cd .gradle/wrapper/dists
wget http://file.hualongdata.com/gradle-3.5.1-bin.tar.gz
tar zxf gradle-3.5.1-bin.tar.gz
```


