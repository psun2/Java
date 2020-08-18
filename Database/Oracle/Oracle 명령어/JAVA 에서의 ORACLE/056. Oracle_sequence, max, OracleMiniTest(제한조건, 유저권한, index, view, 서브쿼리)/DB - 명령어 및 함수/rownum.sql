-- web의 페이지당 반환할 데이터의 갯수파악을 위한 rownum
-- 보통 여러 정보를 함께 담고 있는 가상의 테이블인 view 와 함께 사용 됩니다.

select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by regdate desc; -- 정렬이 끝난뒤 서브쿼리 형식으로 rownum 을 붙여 주어야 합니다.

select rownum rnum, t1.* from (select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by regdate desc) t1;

-- rownum에 조건 달기
select rownum rnum, t1.* from (select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by regdate desc) t1 
where rownum >=1 and rownum <= 5;

-- view 로 만들기
create view res_exam as
select rownum rnum, t1.* from (select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by regdate desc) t1;

select * from user_views;

select * from res_exam;

-- view 를 활용하여 만들어진 가상테이블에 rownum 조건 달기
select * from res_exam
where rownum >=1 and rownum <= 5;

-- 만들어져있는 exam_id sequence 과 만들어진 res_exam view 를 활용한 insert
insert into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'bbb', '기말', '1995-05-05', 100, 100);

select * from res_exam;