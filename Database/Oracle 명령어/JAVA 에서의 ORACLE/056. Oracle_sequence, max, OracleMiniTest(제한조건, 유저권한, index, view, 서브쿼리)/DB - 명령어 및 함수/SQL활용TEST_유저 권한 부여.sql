-- 권한 부여
grant create view to test_1;

-- 권한 확인
select * from dba_sys_privs where grantee = 'TEST_1';