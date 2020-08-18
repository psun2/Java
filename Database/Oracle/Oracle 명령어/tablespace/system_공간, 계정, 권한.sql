--create tablespace korea_2 ( : 공간생성)
--datafile 'D:\Oracle\korea_temp.dbf' ( : 파일 경로)
--size 100000000; ( : 사이즈)

create tablespace korea_2
datafile 'D:\Oracle\korea_temp.dbf'
size 100000000;

select * from dba_tablespaces;
-- KOREA_2

create user korea_b identified by 123456
default tablespace korea_2
temporary tablespace temp;

select * from all_users;
-- KOREA_B

-- 테이블스페이스 목록
select * from dba_tablespaces;

-- 데이터 파일
select * from dba_data_files;
-- D:\ORACLE\KOREA_TEMP.DBF

-- 컨트롤 파일
select * from v$controlfile;

-- log 파일
select * from v$log;

select * from v$logfile;

select * from v$log t1, v$logfile t2
where t1.group# = t2.group#;

--------------------------------------------------------------------------------------------------------------------------------------

-- 권한 부여 (grant : 승인하다)
모든 권한 목록  
connect  
resource  
create table  
create view  
create sequence

grant connect, resource, create table to korea_b;

-- 권한 확인
select * from dba_sys_privs where grantee = 'KOREA_B';
--KOREA_B	CREATE TABLE	NO
--KOREA_B	UNLIMITED TABLESPACE	NO

-- 권한 삭제 (revoke : 폐지하다)
모든 권한 목록  
connect  
resource  
create table  
create view  
create sequence

revoke resource, connect, create table from korea_b;

-- 권한 재 확인
select * from dba_sys_privs where grantee = 'KOREA_B';

-- 계정 수정 (alter : 변질시키다)
alter user korea_b identified by 987654;

-- 계정삭제 (drop : 떨어뜨리다)
drop user korea_b cascade;

-- 테이블스페이스 삭제 (drop : 떨어뜨리다)
drop tablespace korea_2;

-- 삭제 확인
select * from all_users;
select * from dba_tablespaces;

--------------------------------------------------------------------------------------------------------------------------------------

예제) 테이블 스페이스 tp_2를 생성하세요. 용량 : 100mb

계정을 생성하고 권한을 부여하세요
user_b / 123456

-- 공간생성
create tablespace tp_2
datafile 'D:\Oracle\tp_2.dbf'
size 102400;

-- 유저생성
create user user_b identified by 123456
default tablespace tp_2
temporary tablespace temp;

-- 권한부여
grant 
connect,  
resource,  
create table,  
create view,
create sequence
to user_b;