/* �׷��Լ� */

select sum(kor) from exam;
-- 823

select sum(kor), sum(eng), sum(mat) from exam;
-- 823	862	858

--select title,
--sum(kor), avg(kor), max(kor), min(kor), count(kor),
--sum(eng), avg(eng), max(eng), min(eng), count(eng),
--sum(math), avg(math), max(math), min(math), count(math)
--from exam; -- error 
--�׷��Լ��� �����͸� ��� ������ �մϴ�. �� �������� �ʴ��� ���� �Դϴ�.

select 
title,
sum(kor), avg(kor), max(kor), min(kor), count(kor),
sum(eng), avg(eng), max(eng), min(eng), count(eng),
sum(mat), avg(mat), max(mat), min(mat), count(mat)
from exam
group by title;
--	277	55.4	97	17	5	273	54.6	97	27	5	368	73.6	97	43	5
--�⸻	320	64	98	18	5	330	66	86	36	5	317	63.4	85	35	5
--�߰�	226	75.33333333333333333333333333333333333333	100	58	3	259	86.33333333333333333333333333333333333333	97	66	3	173	57.66666666666666666666666666666666666667	95	13	3

select title, hakgi, sum(kor) from exam -- 2
where kor >= 60 -- ó������ 1
group by title, hakgi;

--		243
--�⸻	1	254
--�߰�	2	168

-- ����� 60�� �̻��� ������� ���� �հ踦 ���µ�, �� �հ谡 200�� �������� ����ϰڽ��ϴ�.
select title, hakgi, sum(kor) from exam -- 2
where kor >= 60 -- ó������ 1
group by title, hakgi
having sum(kor) >= 200 -- 3;

--		243
--�⸻	1	254

-- ���� where �� ó�������� �ٸ�.
-- having�� �������� ���� ���� �� ������ ������ �˻�
-- where�� �������� �Ÿ���, ������