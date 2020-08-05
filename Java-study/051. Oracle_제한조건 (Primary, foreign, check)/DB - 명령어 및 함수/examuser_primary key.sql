시험테이블을 정의하세요 exam
no, title, sid, kor, eng, mat, regdate
시험번호, 제목, 학생id, 국어, 영어, 수학, 시험일
숫자, 문자, 문자, 숫자, 날자(date)

create table exam(
no number(4),
title varchar2(20),
sid varchar2(20),
kor number(3),
eng number(3),
mat number(3),
regdate date
);

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 테이블 생성시 primary key 부여
create table stud(
id varchar2(20) primary key,
name varchar2(50) constraint stud_name_nn not null,
addr varchar2(300),
tel varchar2(50) constraint stud_tel_uni unique
);

-- 키 확인
select * from user_constraints;

-- 생성된 테이블에 키 추가
alter table exam add constraint exam_no_pk primary key (no);

-- 키 재확인
select * from tab;
select * from user_constraint where tname = 'EXAM'; -- 이건 안되네 ?
select * from user_constraints;

-- 키 제거
alter table stud drop constraint stud_tel_uni;
alter table stud drop constraint stud_name_nn;
alter table stud drop constraint SYS_C007046;

-- 키 재확인
select * from user_constraints;

-- 생성된 테이블에 key 값 추가
alter table stud add constraint stud_id_pk primary key(id);
alter table stud add constraint stud_tel_uni unique(tel);

-- 생성된 테이블에 not null 키 값으로 수정
alter table stud modify name constraint stud_name_nn not null;

-- 키 재확인
select * from user_constraints;

-- 형식에 맞게 삽입
select * from stud;
select * from user_tab_columns;
select * from user_tab_columns where table_name = 'STUD';
insert into stud (id, name, addr, tel) values ('aaa', '정우성', '우리집', '1111');

-- Error 상황
insert into stud (id, name, addr, tel) values ('aaa', '정남성', '니네집', '2222'); -- primary key 겹침
insert into stud (name, addr, tel) values ('정남성', '니네집', '2222'); -- primary key로 등록한 아이디가 없어도 Error
insert into stud (id, addr, tel) values ('bbb', '니네옆집', '2222'); -- not null 설정인 이름이 null 이여도 Error
insert into stud (id, name, addr, tel) values ('bbb', '정남성', '니네집', '1111'); -- unique 한 tel이 다른사람과 겹쳐도 Error

select * from stud;

-- 정상적 inset 
insert into stud (id, name, addr) values ('bbb', '정남성', '니네집'); -- tel 은 unique 하지만 not null 이 아니므로 null 일때 정상 삽입
insert into stud (id, name) values ('ccc', '정동성');
insert into stud (id, name, addr, tel) values ('ddd', '정북성', '니네집', '2222');

select * from stud;