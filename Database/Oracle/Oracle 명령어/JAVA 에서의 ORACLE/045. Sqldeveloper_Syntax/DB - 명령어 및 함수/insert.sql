-- ���� ����Ű : ctrl + enter
-- �ڵ� ���� : ctrl + F7

-- insert : ����
insert 
into member (no, id, name, hobby, height, regdate, birth)
values (1, 'tjddjs90', '�ڼ���', '�ڵ�', 175.5, '1990-05-12 12:00:00', '1990-05-12');

-- insert : �κ� ���� ����
insert
into member (no, id, name, birth) values (2, 'kyj1211', '�迵��', '1990-12-11');

-- insert : ���� ���� ����
insert all
into member (no, id, name, birth) values(3, 'dkfdkdy', '�˾ƿ�', '2020-07-13')
into member (no, id, name, birth) values(4, 'rmftpdy', '�۽��', '1980-02-24')
into member (no, id, name, birth) values(5, 'ghdrlfrhd', 'ȫ�浿', '1150-04-25')
into member (no, id, name, birth) values(6, 'zmflrmaktm', 'ũ��������', '1998-12-25')
into member (no, id, name, birth) values(7, 'tlrahrdlf', '�ĸ���', '2001-04-05')
select * from dual;