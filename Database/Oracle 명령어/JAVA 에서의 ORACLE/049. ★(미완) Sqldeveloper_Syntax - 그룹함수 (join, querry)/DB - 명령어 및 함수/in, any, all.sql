/* in */
-- ?? any�� ���

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

/* any */
-- ������ � ���̶� �´� ���� ��ȯ

select * from exam where kor >= any (32, 72, 78);
-- �� 32 �̻��� ���� ��ȯ
select * from exam where kor >= any (select kor from exam where pid = 'bbb');
-- exam�� pid �� bbb �϶� kor ������ �ּҰ����� ���� ������ ��ȯ

/* all */
-- ������ ��� ������Ű�� ���� ��ȯ
select * from exam where kor >= all (32, 72, 78);
-- �� 78 �̻��� ��ȯ

select * from exam where kor >= all (select kor from exam where pid = 'bbb');
-- exam�� pid �� bbb �϶� kor ������ �ִ밪���� ���� ������ ��ȯ

select * from exam where kor >= (select kor from exam where pid = 'bbb' and kor is not null);