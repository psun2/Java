/* querry */

select rownum �ο��, member.* from member;

--select : �����ϴ�
--rownum : ����ȣ �� �ִ� Oracle�� ���� �Լ�
--�ο�� : �÷���
--member.* : member �� ��� �÷���
--from : ~ �κ���
--member : member ���̺�

select member.*, rownum �ο�� from member;

select rownum �ο��, member.* from member
order by name; 

--select : �����ϴ�
--rownum : ����ȣ �� �ִ� Oracle�� ���� �Լ�
--�ο�� : �÷���
--member.* : member �� ��� �÷���
--from : ~ �κ���
--member : member ���̺�
--order by : �����ϰٴ�
--name : �̸� ������ (���� ����)

select rownum �ο��, member.* from member
order by name desc;

--select : �����ϴ�
--rownum : ����ȣ �� �ִ� Oracle�� ���� �Լ�
--�ο�� : �÷���
--member.* : member �� ��� �÷���
--from : ~ �κ���
--member : member ���̺�
--order by : �����ϰٴ�
--name : �̸� ������ 
-- desc : ���� ����

/* ���� ���� */
/* ���� �Ǿ� �ִ� ���̺�� ���ٴ� */
select rownum �ο��, querry.* from (select * from member order by name) querry;

-- querry ������� : ���ĵ� ���·� �� ���ε� ���� ���������� ���İ� ���ÿ� rownum�� �������� �߻�
-- ���� ������ �ѹ��� ��ä�� ���� ���ڴ�.

--select : �����ϴ�
--rownum : ���� ��ȣ�� �����ϴ� Oracle �� �����Լ�
--�ο�� : �÷���
--querry : �ӽ� ���� ��
--querry.* : querry �� ��� �÷� 
--from : ~ �� ����
--(select * from member order by name) : ��ä�� ���� ���̺�
--�� ���� ������ member ���̺�
--querry : ������ �����ϱ� ���� �ӽ� ������

--1	2	ĥ����	�迵��	��ȭ	159.9	19/10/13 00:00:00.000000000	90/12/11
--2	1	�Ѱ���	�ڼ���	Tv����	175.5	19/10/13 00:00:00.000000000	90/05/12
--3	7	�ﰡ��	�ĸ���	���ڱ�		20/03/02 00:00:00.000000000	01/04/05
--4	6	�ʰ���	ũ��������	��ȭ		20/03/02 00:00:00.000000000	98/12/25
--5	1	������	ȣ��ȣ��	�ĸ���	1	15/05/05 00:00:00.000000000	88/08/08
--6	5	�Ѱ���	ȫ�浿	��ȭ		20/02/06 13:00:00.000000000	50/04/25

 /* �������� - ���̺�ε� ����ϰ�, �� ������ �÷����ε� ��� ���� */
 /* join �� �����ϳ� join ���� �� ���� ���� �� �� �ִ� */
select avg(kor) from exam;

-- 63.30769230769230769230769230769230769231 

select kor from exam;
 
select exam.* from exam where kor >= (select avg(kor) from exam);
--select : �����ϴ�
--exam.* : exam �� ��� �÷�
--* ���� exam.* �� ����ϴ����� : where�� ������ ���⶧���� exam�� kor�̶�� ���� �� �ƾ� �ϱ� ����
--from : ~ �� ����
--exam : exam ���̺�
--where : (���ǹ�) ���
--kor : kor �÷���
--(select avg(kor) from exam) : (������ �׻� ��ȣ �ȿ��� ����մϴ�.) exam�� kor�÷��� �������
--���� ���ų� ũ�ٸ� ���

--�Ѱ���	97	97	97			�Ѱ���
--�ΰ���	72	37	74			bbb
--�簡��	74	47	77			aaa
--������	78	56	35	1	�⸻	������
--ĥ����	98	86	85	1	�⸻	bbb
--�Ȱ���	78	76	75	1	�⸻	aaa
--���̰���	68	66	65	2	�߰�	fff
--�ʻﰡ��	100	97	13	2	�߰�	ccc

select max(no) + 1 as maxnum from exam;
select max(no) + 1 as "maxnum + 1" from exam;
-- 14

--select : �����ϴ�
--max(no) + 1 : max => �ִ밪�� ��ȯ�ϴ� Oracle �� �����Լ� �ִ밪 + 1
--as : �μ� �÷����� ����
--"maxnum + 1" :  ����ǥ ���� ��� ���� �ϳ� ����� �� ������ �ֵ���ǥ�� ���� ��� ����
--from : ~ �κ���
--exam : exam ���̺�

insert into exam (no, pid, hakgi, title, kor, eng, mat) values (14, 'aaa', 2, '�߰�', 56, 63, 51);

--insert : ����
--into : ~ ��
--exam : exam ���̺�
--(no, pid, hakgi, title, kor, eng, mat) : ���� ������ �÷���
--�÷����� ������ null�� ����
--values (14, 'aaa', 2, '�߰�', 56, 63, 51) : ���� (����)

insert into exam (no, pid, hakgi, title, kor, eng, mat) values
((select max(no) +1 from exam), 'bbb', 2, '�߰�', 76, 73, 71);
--insert : ����
--into : ~ ��
--exam : exam ���̺�
--(no, pid, hakgi, title, kor, eng, mat) : ���� ������ �÷���
--�÷����� ������ null�� ����
--values : ����
--((select max(no) +1 from exam), 'bbb', 2, '�߰�', 76, 73, 71) : (select max(no) +1 from exam) =>
--querry ��� : ������ ���� ū��ȣ���� +1 �� ���� �־� �ֱ� ���Ͽ� ������ ���

select pid, mat from exam where mat >= 80;
--select : �����ϴ�
--pid : ������ �÷���
--from : ~ �� ����
--exam : exam ���̺�
--where : (���ǹ�) ���
--mat : mat �÷�
--�� 80 �̻��� pid��...

--�Ѱ���	97
--ĥ����	85
--������	85
--���ϰ���	95

select exam.* from exam where mat >= 80;
--�Ѱ���	97	97	97			�Ѱ���	1
--ĥ����	98	86	85	1	�⸻	bbb	7
--������	18	36	85	2	�⸻	fff	9
--���ϰ���	58	96	95	2	�߰�	���ϰ���	11

select * from exam where pid in('bbb', 'ddd', 'eee');
--select : �����ϴ�
--* : all (�����)
--from : ~ �� ����
--member : ���̺��
--where (���ǹ�) ���
--id : �÷����� id �� �÷�
--in : ������ ���� ��� ������ �´ٸ�
-- �� id �� bbb, ddd, eee �� ���鸸 ���ɴϴ�

select * from member where id in(select pid from exam where mat >= 80);

--select : �����ϴ�
--* : all �÷� (�����)
--from ~ �� ����
--member : member ���̺�
--where : ~ �� ����
--id : id ��
--in : ��ȣ ���� ��� ������...
--(select pid from exam where mat >= 80) : ������ ���
--�������� ��������ν� �ϳ��� �������� ���ٴ�..
--�� �ٸ� ���̺��� ���� ������ ����ϰ� �;� ������ ���

--5	�Ѱ���	ȫ�浿	��ȭ		20/02/06 13:00:00.000000000	50/04/25
--1	�Ѱ���	�ڼ���	Tv����	175.5	19/10/13 00:00:00.000000000	90/05/12
--2	ĥ����	�迵��	��ȭ	159.9	19/10/13 00:00:00.000000000	90/12/11