
# Hibernate

需要在Mysql中创建表`EMPLOYEE`,这里的数据库名称为`testDB`，需要在`hibernate.cfg.xml`中配置。

```mysql
create table EMPLOYEE (
   id INT NOT NULL auto_increment,
   first_name VARCHAR(20) default NULL,
   last_name  VARCHAR(20) default NULL,
   salary     INT  default NULL,
   PRIMARY KEY (id)
);
```