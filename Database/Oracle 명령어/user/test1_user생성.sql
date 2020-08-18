-- 유저생성

create user test_1 identified by 123456
default tablespace ts1
temporary tablespace temp;

select * from all_users;