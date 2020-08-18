--create tablespace korea_2 ( : ��������)
--datafile 'D:\Oracle\korea_temp.dbf' ( : ���� ���)
--size 100000000; ( : ������)

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

-- ���̺����̽� ���
select * from dba_tablespaces;

-- ������ ����
select * from dba_data_files;
-- D:\ORACLE\KOREA_TEMP.DBF

-- ��Ʈ�� ����
select * from v$controlfile;

-- log ����
select * from v$log;

select * from v$logfile;

select * from v$log t1, v$logfile t2
where t1.group# = t2.group#;

--------------------------------------------------------------------------------------------------------------------------------------

-- ���� �ο� (grant : �����ϴ�)
��� ���� ���  
connect  
resource  
create table  
create view  
create sequence

grant connect, resource, create table to korea_b;

-- ���� Ȯ��
select * from dba_sys_privs where grantee = 'KOREA_B';
--KOREA_B	CREATE TABLE	NO
--KOREA_B	UNLIMITED TABLESPACE	NO

-- ���� ���� (revoke : �����ϴ�)
��� ���� ���  
connect  
resource  
create table  
create view  
create sequence

revoke resource, connect, create table from korea_b;

-- ���� �� Ȯ��
select * from dba_sys_privs where grantee = 'KOREA_B';

-- ���� ���� (alter : ������Ű��)
alter user korea_b identified by 987654;

-- �������� (drop : ����߸���)
drop user korea_b cascade;

-- ���̺����̽� ���� (drop : ����߸���)
drop tablespace korea_2;

-- ���� Ȯ��
select * from all_users;
select * from dba_tablespaces;

--------------------------------------------------------------------------------------------------------------------------------------

����) ���̺� �����̽� tp_2�� �����ϼ���. �뷮 : 100mb

������ �����ϰ� ������ �ο��ϼ���
user_b / 123456

-- ��������
create tablespace tp_2
datafile 'D:\Oracle\tp_2.dbf'
size 102400;

-- ��������
create user user_b identified by 123456
default tablespace tp_2
temporary tablespace temp;

-- ���Ѻο�
grant 
connect,  
resource,  
create table,  
create view,
create sequence
to user_b;