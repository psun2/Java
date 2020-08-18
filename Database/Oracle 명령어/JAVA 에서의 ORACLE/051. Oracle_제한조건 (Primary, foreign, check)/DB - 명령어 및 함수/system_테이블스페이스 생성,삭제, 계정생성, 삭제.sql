select * from dba_data_files;

-- 테이블 스페이스 사이즈 변경
alter database datafile 'D:\ORACLE\TP_2.DBF' resize 100000000;

select * from all_users;

-- 계정 삭제
drop user user_b cascade;

select * from dba_tablespaces;

-- 테이블 스페이스 삭제
drop tablespace TP_2;

------------------------------------------------------------------------------------------------------------------
-- 테이블스페이스 생성
create tablespace examtablespace
datafile 'D:\Oracle\examtablespace.dbf'
size 100000000;

-- 유저 생성
create user examuser identified by 123456
default tablespace examtablespace
temporary tablespace temp;

-- 권한 부여
grant connect, resource, create table, create view, create sequence to examuser;

