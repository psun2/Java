-- select order by (����) Exam
-- ����� 60�� �̻��� �л����� �б� ����, Ÿ��Ʋ �ø� ��� ���� ������ �����Ͽ� ����ϼ���.

INSERT ALL 
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('������',78,56, 35, 1,'�⸻')
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('ĥ����',98,86,85, 1,'�⸻')
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('�Ȱ���',78,76,75,1,'�⸻')  
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('������',18,36,85,2,'�⸻') 
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('�ʰ���',48,76,37,2,'�⸻') 
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('���ϰ���',58,96,95,2,'�߰�')  
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('���̰���',68,66,65,2,'�߰�') 
INTO exam (name, kor, eng, mat, hakgi, title) VALUES ('�ʻﰡ��',100,97,13,2,'�߰�') 
SELECT * FROM dual;

select 
exam.*, 
kor + eng + mat as tot, 
(kor + eng + mat) / 3 as avg 
from exam
where (kor + eng + mat) / 3 >= 60
order by hakgi desc, title, (kor + eng + mat) / 3 desc;

--�Ѱ���	97	97	97			291	97
--�簡��	74	47	77			198	66
--�ΰ���	72	37	74			183	61
--���ϰ���	58	96	95	2	�߰�	249	83
--�ʻﰡ��	100	97	13	2	�߰�	210	70
--���̰���	68	66	65	2	�߰�	199	66.33333333333333333333333333333333333333
--ĥ����	98	86	85	1	�⸻	269	89.66666666666666666666666666666666666667
--�Ȱ���	78	76	75	1	�⸻	229	76.33333333333333333333333333333333333333