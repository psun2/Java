-- ���̺� �׸� ��ȸ : table_name, tablespace_name, ini_trans
select * from all_all_tables;
select * from all_all_tables where owner = 'TEST_1';
select table_name, tablespace_name, ini_trans from all_all_tables where owner = 'TEST_1';

-- �ֹ� ���̺� �÷� ��ȸ �׸� : column_name, data_type, data_length, nullable, data_default
select * from user_tab_columns;
select column_name, data_type, data_length, nullable, data_default from user_tab_columns;