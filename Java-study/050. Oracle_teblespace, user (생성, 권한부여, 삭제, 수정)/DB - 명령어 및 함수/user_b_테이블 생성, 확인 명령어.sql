create table test1 (
id number(4),
name char(10),
email varchar2(50),
height number(6, 2), -- 6�ڸ� ���� �Ҽ����� ���ڸ���� �ǹ�
birth date,
regdate timestamp,
hobby nvarchar2(10)
);

-- ���� ��� ���̺� ����
select * from tab;
--TEST1	TABLE	

-- ����ڰ� ���� ���� ��� �÷� ����
select * from user_tab_columns;

select * from user_tab_columns where table_name = 'TEST1';

select * from test1;