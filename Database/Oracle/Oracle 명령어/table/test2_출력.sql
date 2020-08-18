-- 테이블 항목 조회 : table_name, tablespace_name, ini_trans
select * from all_all_tables;
select * from all_all_tables where owner = 'TEST_1';
select table_name, tablespace_name, ini_trans from all_all_tables where owner = 'TEST_1';

-- 주문 테이블 컬럼 조회 항목 : column_name, data_type, data_length, nullable, data_default
select * from user_tab_columns;
select column_name, data_type, data_length, nullable, data_default from user_tab_columns;