-- ���Ѻο�
���Ѹ��
connect
resource
create table
create view
create sequence

grant resource, connect to test_1;

-- ���� Ȯ��
select * from dba_sys_privs;
select * from dba_sys_privs where grantee = 'TEST_1';

-- ���� ����
revoke resource, connect from test_1;

select * from dba_sys_privs where grantee = 'TEST_1';