CREATE DATABASE IF NOT EXISTS mybatis_demo;
CREATE USER IF NOT EXISTS 'demo'@'%' IDENTIFIED BY 'demo';
GRANT ALL ON mybatis_demo.* TO 'demo'@'%';
CREATE USER IF NOT EXISTS 'demo'@'localhost' IDENTIFIED BY 'demo';
GRANT ALL ON mybatis_demo.* TO 'demo'@'localhost';
FLUSH PRIVILEGES;