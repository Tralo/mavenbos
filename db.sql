create database bos default charset utf8 collate utf8_general_ci;

# 创建用户
create user sh0731@localhost identified by 'sh0731';

# 授予权限
grant all on bos.* to sh0731@localhost;