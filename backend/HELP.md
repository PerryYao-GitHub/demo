# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.18/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.18/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.18/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)

# 开始首先替换数据库名字 !!!!!

# MySQL 脚本
```mysql
create table t_user
(
    id          int auto_increment
        primary key,
    account     varchar(36)       null,
    password    varchar(36)       not null,
    name        varchar(18)       null,
    avatar      varchar(320)      null,
    phone       varchar(20)       null,
    email       varchar(36)       null,
    tags        varchar(512)      null,
    status      tinyint           not null,
    role        tinyint           not null,
    login_time  datetime          null,
    create_time datetime          null,
    update_time datetime          null,
    deleted     tinyint default 0 not null
);

```
