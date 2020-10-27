CREATE DATABASE IF NOT EXISTS demo;
CREATE USER IF NOT EXISTS '{{mysql_username}}'@'%' IDENTIFIED BY '{{mysql_password}}';
GRANT ALL ON demo.* TO '{{mysql_username}}'@'%';
FLUSH PRIVILEGES;