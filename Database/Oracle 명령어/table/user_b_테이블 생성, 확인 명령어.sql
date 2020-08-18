create table test1 (
id number(4),
name char(10),
email varchar2(50),
height number(6, 2), -- 6자리 수에 소수점이 두자리라는 의미
birth date,
regdate timestamp,
hobby nvarchar2(10)
);

-- 현재 모든 테이블 보기
select * from tab;
--TEST1	TABLE	

-- 사용자가 만든 탭의 모든 컬럼 보기
select * from user_tab_columns;

select * from user_tab_columns where table_name = 'TEST1';

select * from test1;