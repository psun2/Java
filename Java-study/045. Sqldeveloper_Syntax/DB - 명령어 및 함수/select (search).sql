/* select */
select id,name, birth from member where id = 'tjddjs90';
-- tjddjs90	�ڼ���	90/05/12

select id,name, birth from member where id != 'tjddjs90';
--kyj1211	�迵��	90/12/11
--rmftpdy	ȣ��ȣ��	88/08/08
--ghdrlfrhd	ȫ�浿	50/04/25
--zmflrmaktm	ũ��������	98/12/25
--tlrahrdlf	�ĸ���	01/04/05

select * from member where id != 'tjddjs90';
--2	kyj1211	�迵��	��ȭ		19/10/13 00:00:00.000000000	90/12/11
--1	rmftpdy	ȣ��ȣ��	�ĸ���	1	15/05/05 00:00:00.000000000	88/08/08
--5	ghdrlfrhd	ȫ�浿	��ȭ		20/02/06 13:00:00.000000000	50/04/25
--6	zmflrmaktm	ũ��������	��ȭ		20/03/02 00:00:00.000000000	98/12/25
--7	tlrahrdlf	�ĸ���	���ڱ�		20/03/02 00:00:00.000000000	01/04/05