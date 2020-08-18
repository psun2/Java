/* 그룹함수 - exam(having, where, group by, order by) */

--exam 
-- 시험 종류별로 국어, 영어, 수학의 최대점수를 출력하세요
-- 단 학생 평균이 70점 이상인 사람들만 그룹화 할것
-- 시험 종류는 오름 차순

select name, title, hakgi,  max(kor), max(eng), max(mat)
from exam
where trunc(nvl(kor, 0) + nvl(eng, 0) + nvl(mat, 0)) / 3 >= 70
group by name, title, hakgi
order by title desc;

--한가인			97	97	97
--십삼가인	중간	2	100	97	13
--십일가인	중간	2	58	96	95
--칠가인	기말	1	98	86	85
--팔가인	기말	1	78	76	75