/* 그룹함수 */

select sum(kor) from exam;
-- 823

select sum(kor), sum(eng), sum(mat) from exam;
-- 823	862	858

--select title,
--sum(kor), avg(kor), max(kor), min(kor), count(kor),
--sum(eng), avg(eng), max(eng), min(eng), count(eng),
--sum(math), avg(math), max(math), min(math), count(math)
--from exam; -- error 
--그룹함수는 데이터를 묶어서 나오게 합니다. 즉 묶여있지 않는한 에러 입니다.

select 
title,
sum(kor), avg(kor), max(kor), min(kor), count(kor),
sum(eng), avg(eng), max(eng), min(eng), count(eng),
sum(mat), avg(mat), max(mat), min(mat), count(mat)
from exam
group by title;
--	277	55.4	97	17	5	273	54.6	97	27	5	368	73.6	97	43	5
--기말	320	64	98	18	5	330	66	86	36	5	317	63.4	85	35	5
--중간	226	75.33333333333333333333333333333333333333	100	58	3	259	86.33333333333333333333333333333333333333	97	66	3	173	57.66666666666666666666666666666666666667	95	13	3

select title, hakgi, sum(kor) from exam -- 2
where kor >= 60 -- 처리순서 1
group by title, hakgi;

--		243
--기말	1	254
--중간	2	168

-- 평균이 60점 이상인 사람들의 국어 합계를 보는데, 그 합계가 200이 넘을때만 출력하겠습니다.
select title, hakgi, sum(kor) from exam -- 2
where kor >= 60 -- 처리순서 1
group by title, hakgi
having sum(kor) >= 200 -- 3;

--		243
--기말	1	254

-- 위의 where 와 처리순서가 다름.
-- having은 구룹으로 먼저 묶고 그 다음에 조건을 검색
-- where는 조건으로 거른뒤, 구룹핑