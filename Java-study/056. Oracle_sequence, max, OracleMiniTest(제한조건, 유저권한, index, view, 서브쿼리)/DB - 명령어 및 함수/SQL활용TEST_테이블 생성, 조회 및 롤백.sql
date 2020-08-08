1. 학생 테이블과 시험 테이블을 생성하세요

학생테이블(stud)

id(id) : 문자(20byte) PK

이름(name) : 문자(50byte) not null

시험테이블(exam)

id(id): 숫자

학생id(pid) : 문자(20byte) FK

제목(title) : 문자(50byte)

시험일(regdate) : 날짜

국어(kor) : 정수

영어(eng) : 정수

2. 학생 정보와 시험정보를 입력하세요

id 이름

aaa 현빈

bbb 원빈

ccc 투빈

ddd 젤리빈

eee 장희빈

3. 시험정보를 입력 및 rollback 시키고 시험정보를 출력하세요

rollback 전 조회

id 학생ID 제목 시험일 국어 영어

1 aaa 중간 18/05/04 67 78

2 bbb 중간 18/05/04 57 58

3 ccc 중간 18/05/04 97 98

4 ddd 중간 18/05/04 77 71

5 aaa 중간 18/05/04 87 82

5 aaa 기말 18/07/10 57 52

6 bbb 기말 18/07/10 97 92

7 ccc 기말 18/07/10 87 82

8 eee 기말 18/07/10 67 62

rollback 후 조회

1 aaa 중간 18/05/04 67 78

2 bbb 중간 18/05/04 57 58

3 ccc 중간 18/05/04 97 98

4 ddd 중간 18/05/04 77 71

 

4. 현재 접속한 계정(test_1) 이 소유한 테이블을 출력하세요

출력내용 : table_name, tablespace_name

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- 학생 테이블 생성
create table stud(
id varchar2(20) constraint stud_id_pk primary key,
name varchar2(50) constraint stud_name_nn not null
);

-- 학생테이블 정보 출력
select * from user_constraints where table_name = 'STUD';
select * from all_all_tables where owner = 'TEST_1';

-- 시험 테이블 생성
create table exam(
id number(4),
pid varchar2(20) constraint exam_pid_fk references stud(id),
title varchar2(50),
regdate date,
kor int,
eng int
);

-- 시험테이블 정보 출력
select * from user_constraints where table_name = 'EXAM';
select * from all_all_tables where owner = 'TEST_1';

-- 학생테이블 정보 입력
insert all
into stud (id, name) values ('aaa', '현빈')
into stud (id, name) values ('bbb', '원빈')
into stud (id, name) values ('ccc', '투빈')
into stud (id, name) values ('ddd', '젤리빈')
into stud (id, name) values ('eee', '장희빈')
select * from dual;

-- 학생테이블 출력
select * from stud;

-- 시험테이블 정보 입력
insert into exam (id, pid, title, regdate, kor, eng) values (1, 'aaa', '중간', '18/05/04', 67, 78);
insert into exam (id, pid, title, regdate, kor, eng) values (2, 'bbb', '중간', '18/05/04', 57, 58);
insert into exam (id, pid, title, regdate, kor, eng) values (3, 'ccc', '중간', '18/05/04', 97, 98);
insert into exam (id, pid, title, regdate, kor, eng) values (4, 'ddd', '중간', '18/05/04', 77, 71);
commit;
insert into exam (id, pid, title, regdate, kor, eng) values (5, 'aaa', '중간', '18/05/04', 87, 82);
insert into exam (id, pid, title, regdate, kor, eng) values (5, 'aaa', '기말', '18/07/10', 57, 52);
insert into exam (id, pid, title, regdate, kor, eng) values (6, 'bbb', '기말', '18/07/10', 97, 92);
insert into exam (id, pid, title, regdate, kor, eng) values (7, 'ccc', '기말', '18/07/10', 87, 82);
insert into exam (id, pid, title, regdate, kor, eng) values (8, 'eee', '기말', '18/07/10', 67, 62);

-- 시험테이블 출력
select * from exam;

-- 시험테이블 롤백
rollback;

-- 롤백후 시험테이블 출력
select * from exam;

-- 접속한 계정이 소유한 테이블 출력
select table_name, tablespace_name from all_all_tables where owner = 'TEST_1';


--------------------------------------------------------------------------------
create table ttt(
id varchar2(30)
);

insert into ttt (id) values ('qqq');

delete ttt;

--------------------------------------------------------------------------------