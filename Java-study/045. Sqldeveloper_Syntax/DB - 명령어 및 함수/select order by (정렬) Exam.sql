-- select order by (정렬) Exam
-- 평균이 60점 이상인 학생들을 학기 내림, 타이틀 올림 평균 내림 순으로 정렬하여 출력하세요.

INSERT ALL 
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('육가인',78,56, 35, 1,'기말')
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('칠가인',98,86,85, 1,'기말')
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('팔가인',78,76,75,1,'기말')  
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('구가인',18,36,85,2,'기말') 
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('십가인',48,76,37,2,'기말') 
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('십일가인',58,96,95,2,'중간')  
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('십이가인',68,66,65,2,'중간') 
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('십삼가인',100,97,13,2,'중간') 
SELECT * FROM dual;

select 
exam.*, 
kor + eng + mat as tot, 
(kor + eng + mat) / 3 as avg 
from exam
where (kor + eng + mat) / 3 >= 60
order by hakgi desc, title, (kor + eng + mat) / 3 desc;

--한가인	97	97	97			291	97
--사가인	74	47	77			198	66
--두가인	72	37	74			183	61
--십일가인	58	96	95	2	중간	249	83
--십삼가인	100	97	13	2	중간	210	70
--십이가인	68	66	65	2	중간	199	66.33333333333333333333333333333333333333
--칠가인	98	86	85	1	기말	269	89.66666666666666666666666666666666666667
--팔가인	78	76	75	1	기말	229	76.33333333333333333333333333333333333333