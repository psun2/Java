-- ��rollback 
insert all
into department (id, name) values('com', '��ǻ�Ͱ���')
into department (id, name) values('eye_design', '�ð�������')
into department (id, name) values('phillos', 'ö��')
into department (id, name) values('chemisrty', 'ȭ��')
select * from dual;
select * from department;
--com	��ǻ�Ͱ���
--eye_design	�ð�������
--phillos	ö��
--chemisrty	ȭ��

update department set id = 'tb' where name = 'ȭ��';
select * from department;
--com	��ǻ�Ͱ���
--eye_design	�ð�������
--phillos	ö��
--tb	ȭ��

rollback;
select * from department;
-- ��� ���� ��

-- �� commit & rollback : Ŀ�Ա��� �ѹ��� �˴ϴ�.
insert into department (id, name) values('com', '��ǻ�Ͱ���');
insert into department (id, name) values('eye_design', '�ð�������');
commit;
insert into department (id, name) values('phillos', 'ö��');
insert into department (id, name) values('chemisrty', 'ȭ��');
update department set id = 'tb' where name = 'ȭ��';
select * from department;
--com	��ǻ�Ͱ���
--eye_design	�ð�������
--phillos	ö��
--tb	ȭ��

rollback;
select * from department;
--com	��ǻ�Ͱ���
--eye_design	�ð�������

delete department; -- ���� ������ ���� ����

-- �� savepoint & rollback to savepoint : savepoint ���� �ѹ��� �˴ϴ�.
insert into department (id, name) values('com', '��ǻ�Ͱ���');
insert into department (id, name) values('eye_design', '�ð�������');
insert into department (id, name) values('phillos', 'ö��');
insert into department (id, name) values('chemisrty', 'ȭ��');
savepoint a;
update department set id = 'tb' where name = 'ȭ��';
select * from department;
--com	��ǻ�Ͱ���
--eye_design	�ð�������
--phillos	ö��
--tb	ȭ��

rollback to a;
select * from department;
--com	��ǻ�Ͱ���
--eye_design	�ð�������
--phillos	ö��
--chemisrty	ȭ��