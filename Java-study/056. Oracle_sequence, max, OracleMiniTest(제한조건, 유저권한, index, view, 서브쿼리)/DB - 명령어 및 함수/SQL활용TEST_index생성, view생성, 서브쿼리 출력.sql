-- index 생성
create index idx_exam on exam(pid);

-- index 조회
select * from user_indexes;

-- view 생성전 쿼리 확인
select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by id desc;

-- view 생성
create view vm_exam as
select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by id desc;

-- view 확인
select * from user_views;

-- 이름이 2글자인 학생 찾기 
select * from stud
where length(name) = 2;

-- 서브쿼리 사용한 출력
select pid, title, kor, eng, tot, avg from vm_exam, (select * from stud
where length(name) = 2) t1
where t1.id = pid;