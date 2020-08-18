-- 결과 출력 
-- tablespace_name, block_size, segment_space_managment, status 

select * from dba_tablespaces;

select tablespace_name, block_size, segment_space_management, status from dba_tablespaces;

-- 생성된 유저 출력
select * from all_users;