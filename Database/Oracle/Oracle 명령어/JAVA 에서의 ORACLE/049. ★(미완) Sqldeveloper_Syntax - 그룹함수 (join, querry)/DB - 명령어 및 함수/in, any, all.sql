/* in */
-- ?? any와 비슷

select * from member where id in(select pid from exam where mat >= 80);

--select : 선택하다
--* : all 컬럼 (모든요소)
--from ~ 로 부터
--member : member 테이블
--where : ~ 로 부터
--id : id 값
--in : 괄호 안의 모든 조건이...
--(select pid from exam where mat >= 80) : 쿼리문 사용
--쿼리문을 사용함으로써 하나의 조건으로 보겟다..
--즉 다른 테이블의 값을 가져와 사용하고 싶어 쿼리를 사용

--5	한가인	홍길동	영화		20/02/06 13:00:00.000000000	50/04/25
--1	한가인	박성언	Tv봐요	175.5	19/10/13 00:00:00.000000000	90/05/12
--2	칠가인	김영재	영화	159.9	19/10/13 00:00:00.000000000	90/12/11

/* any */
-- 조건중 어떤 것이라도 맞는 값을 반환

select * from exam where kor >= any (32, 72, 78);
-- 즉 32 이상인 것을 반환
select * from exam where kor >= any (select kor from exam where pid = 'bbb');
-- exam의 pid 가 bbb 일때 kor 점수의 최소값보다 높은 점수를 반환

/* all */
-- 조건을 모두 충족시키는 값을 반환
select * from exam where kor >= all (32, 72, 78);
-- 즉 78 이상을 반환

select * from exam where kor >= all (select kor from exam where pid = 'bbb');
-- exam의 pid 가 bbb 일때 kor 점수의 최대값보다 높은 점수를 반환

select * from exam where kor >= (select kor from exam where pid = 'bbb' and kor is not null);