insert into test1 (id, name, email, height, birth, regdate, hobby) values (12, 'aa', 'aaa@aaa.com', 169.9, '1990-05-12', '2020-04-28 15:30:20', '코딩하기');

# 자료형

## char 
byte의 수에 맞추어 입력값이 잘리거나, 부족할 시 공백으로 대체하여 꽉 채워 들어 갑니다.

## navarchar2
nvarchar2 는 char 과 varchar2 와는 달리
byte가 아닌 글자수를 parameter 로 받습니다.

## UTF-8 인코딩 방식에서는 한글은 3byte입니다.

-- column 추가
alter table test1 add(spec varchar2(20));

-- column 수정
alter table test1 modify(id varchar2(40));

-- column 수정 Error : 현재 데이터가 들어 있어 수정이 안됩니다. 지우고 다시 시도 합니다.

-- delete
delete from test1;

-- column 수정
alter table test1 modify(id varchar2(40));

-- 변경확인
select * from user_tab_columns where table_name = 'TEST1';
-- TEST1	ID	VARCHAR2

-- column 명 변경
alter table test1 rename column id to pid;

-- column 삭제
alter table test1 drop column spec;

-- table 삭제
drop table test1;

select * from tab;
