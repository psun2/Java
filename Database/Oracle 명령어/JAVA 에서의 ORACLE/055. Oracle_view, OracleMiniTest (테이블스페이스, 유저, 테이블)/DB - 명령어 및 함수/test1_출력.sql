-- ��� ��� 
-- tablespace_name, block_size, segment_space_managment, status 

select * from dba_tablespaces;

select tablespace_name, block_size, segment_space_management, status from dba_tablespaces;

-- ������ ���� ���
select * from all_users;