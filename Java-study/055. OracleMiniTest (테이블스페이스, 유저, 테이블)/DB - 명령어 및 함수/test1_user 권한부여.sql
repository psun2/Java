-- 권한부여
권한목록
connect
resource
create table
create view
create sequence

grant resource, connect to test_1;

-- 권한 확인
select * from dba_sys_privs;
select * from dba_sys_privs where grantee = 'TEST_1';

-- 권한 삭제
revoke resource, connect from test_1;

select * from dba_sys_privs where grantee = 'TEST_1';