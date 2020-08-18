select * from dba_data_files;

-- ���̺� �����̽� ������ ����
alter database datafile 'D:\ORACLE\TP_2.DBF' resize 100000000;

select * from all_users;

-- ���� ����
drop user user_b cascade;

select * from dba_tablespaces;

-- ���̺� �����̽� ����
drop tablespace TP_2;

------------------------------------------------------------------------------------------------------------------
-- ���̺����̽� ����
create tablespace examtablespace
datafile 'D:\Oracle\examtablespace.dbf'
size 100000000;

-- ���� ����
create user examuser identified by 123456
default tablespace examtablespace
temporary tablespace temp;

-- ���� �ο�
grant connect, resource, create table, create view, create sequence to examuser;

