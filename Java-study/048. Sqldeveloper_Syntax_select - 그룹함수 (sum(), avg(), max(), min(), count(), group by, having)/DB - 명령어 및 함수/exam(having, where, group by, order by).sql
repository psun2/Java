/* �׷��Լ� - exam(having, where, group by, order by) */

--exam 
-- ���� �������� ����, ����, ������ �ִ������� ����ϼ���
-- �� �л� ����� 70�� �̻��� ����鸸 �׷�ȭ �Ұ�
-- ���� ������ ���� ����

select name, title, hakgi,  max(kor), max(eng), max(mat)
from exam
where trunc(nvl(kor, 0) + nvl(eng, 0) + nvl(mat, 0)) / 3 >= 70
group by name, title, hakgi
order by title desc;

--�Ѱ���			97	97	97
--�ʻﰡ��	�߰�	2	100	97	13
--���ϰ���	�߰�	2	58	96	95
--ĥ����	�⸻	1	98	86	85
--�Ȱ���	�⸻	1	78	76	75