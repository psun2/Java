-- tablespace 생성

create tablespace ts1
datafile 'D:\Oracle\s1.dbf'
size 100000000;

-- 생성 확인
select * from dba_tablespaces;
