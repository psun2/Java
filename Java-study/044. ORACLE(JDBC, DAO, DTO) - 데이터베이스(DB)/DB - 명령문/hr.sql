-- ���� ����Ű => ctrl + enter

insert into student -- ���� ��ɹ�
(id, name, no, height, regdate, birth, hobby) -- ���� �� column ��� 
-- ex) ���� name �� �ʿ������ name�� ���ָ� �˴ϴ�.
values -- ���� �Է��ϰڴٴ� ��ɹ�
('aaa', '�Ѱ���', 10, 182.7, '2015-05-12 13:22:54', '2020-7-13', 'Tv��û');
-- TIMESTAMP �� DATE �� ���������� �ð����� �ۼ� ���� �մϴ�.

/*
*/

insert into student
(id, name, no, height,  birth)  -- �κ��� ���� ����
values
('bbb','�ΰ���',17,'173.56','1996-7-13');

/* ���� ���� */
insert all
into student (id, name, no, birth) values ('ccc','�ﰡ��',9,'1993-1-2')
into student (id, name, no, birth) values ('ddd','�簡��',12,'1989-06-08')
into student (id, name, no, birth) values ('eee','������',21,'2001-07-23')
into student (id, name, no, birth) values ('fff','������',3,'1997-11-11')
select * from dual;  -- ���� ���� �ϰٴٴ� ��ɹ�

/*������Ʈ*/ -- ���� ������Ʈ ����
update student set regdate ='2020-04-29', hobby = '�ڵ�';

/* ������ ������Ʈ */
update student set hobby = '�Ͼ�~' where id = 'aaa';  -- ���ٸ� ǥ���Ҷ� ��ȣ == �� �ƴ� = �� ��� 
update student set hobby = '���ڱ�' where no >= 10 and no <=20;
update student set hobby = '��ȭ' where no < 10;

-- �ּ�
/* 
    �ּ� ����
            ������
    80���� - 2019/05/08
    90 ~ 95   2019/10/13
    96 ~ 20   2020/03/02
*/

update student set regdate = '2019/05/08' where birth >= '1980-01-01' and birth < '1990-1-1';
update student set regdate = '2019/10/13' where birth >= '1990-01-01' and birth < '1996-01-01';
update student set regdate = '2020/03/02' where birth >= '1996-01-01' and birth < '2021-01-01';


/* ���� */
delete from student where id = 'ccc'; 


/* search(�˻�) */ 
select id,name, birth from student where id = 'ddd';
select id,name, birth from student where id != 'ddd';
select * from student where id != 'ddd';

/* �� �׸� ������Ʈ */
update student set name = 'ȣ��ȣ��', no = 1, height = 1.0, regdate = '2015-05-05', birth = '1988-08-08', hobby = '�ĸ���'
where id = 'eee';

update member set name = '', no = 1, height = 1.0, regdate = '', birth = '', hobby = ''
where id = '';