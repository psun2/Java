/* case -exam */
-- 평균 수우미양가

select exam.*, 
nvl (kor, 0), nvl (eng, 0), nvl (mat, 0),
trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3) as avg,
case
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 10 then '수'
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 9 then '수'
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 8 then '우'
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 7 then '미'
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 6 then '양'
else '가'
end
as grade
from exam;

--한가인	97	97	97			97	97	97	97	수
--두가인	72	37	74			72	37	74	61	양
--삼가인	17	27	77			17	27	77	40	가
--사가인	74	47	77			74	47	77	66	양
--오가인	17	65	43			17	65	43	41	가
--육가인	78	56	35	1	기말	78	56	35	56	가
--칠가인	98	86	85	1	기말	98	86	85	89	우
--팔가인	78	76	75	1	기말	78	76	75	76	미
--구가인	18	36	85	2	기말	18	36	85	46	가
--십가인	48	76	37	2	기말	48	76	37	53	가
--십일가인	58	96	95	2	중간	58	96	95	83	우
--십이가인	68	66	65	2	중간	68	66	65	66	양
--십삼가인	100	97	13	2	중간	100	97	13	70	미