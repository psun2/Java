/* case -exam */
-- ��� ����̾簡

select exam.*, 
nvl (kor, 0), nvl (eng, 0), nvl (mat, 0),
trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3) as avg,
case
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 10 then '��'
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 9 then '��'
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 8 then '��'
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 7 then '��'
when trunc((nvl (kor, 0) + nvl (eng, 0) + nvl (mat, 0)) / 3 / 10) = 6 then '��'
else '��'
end
as grade
from exam;

--�Ѱ���	97	97	97			97	97	97	97	��
--�ΰ���	72	37	74			72	37	74	61	��
--�ﰡ��	17	27	77			17	27	77	40	��
--�簡��	74	47	77			74	47	77	66	��
--������	17	65	43			17	65	43	41	��
--������	78	56	35	1	�⸻	78	56	35	56	��
--ĥ����	98	86	85	1	�⸻	98	86	85	89	��
--�Ȱ���	78	76	75	1	�⸻	78	76	75	76	��
--������	18	36	85	2	�⸻	18	36	85	46	��
--�ʰ���	48	76	37	2	�⸻	48	76	37	53	��
--���ϰ���	58	96	95	2	�߰�	58	96	95	83	��
--���̰���	68	66	65	2	�߰�	68	66	65	66	��
--�ʻﰡ��	100	97	13	2	�߰�	100	97	13	70	��