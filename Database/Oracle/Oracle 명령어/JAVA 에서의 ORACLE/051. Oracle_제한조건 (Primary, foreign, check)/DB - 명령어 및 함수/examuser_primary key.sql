�������̺��� �����ϼ��� exam
no, title, sid, kor, eng, mat, regdate
�����ȣ, ����, �л�id, ����, ����, ����, ������
����, ����, ����, ����, ����(date)

create table exam(
no number(4),
title varchar2(20),
sid varchar2(20),
kor number(3),
eng number(3),
mat number(3),
regdate date
);

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- ���̺� ������ primary key �ο�
create table stud(
id varchar2(20) primary key,
name varchar2(50) constraint stud_name_nn not null,
addr varchar2(300),
tel varchar2(50) constraint stud_tel_uni unique
);

-- Ű Ȯ��
select * from user_constraints;

-- ������ ���̺� Ű �߰�
alter table exam add constraint exam_no_pk primary key (no);

-- Ű ��Ȯ��
select * from tab;
select * from user_constraint where tname = 'EXAM'; -- �̰� �ȵǳ� ?
select * from user_constraints;

-- Ű ����
alter table stud drop constraint stud_tel_uni;
alter table stud drop constraint stud_name_nn;
alter table stud drop constraint SYS_C007046;

-- Ű ��Ȯ��
select * from user_constraints;

-- ������ ���̺� key �� �߰�
alter table stud add constraint stud_id_pk primary key(id);
alter table stud add constraint stud_tel_uni unique(tel);

-- ������ ���̺� not null Ű ������ ����
alter table stud modify name constraint stud_name_nn not null;

-- Ű ��Ȯ��
select * from user_constraints;

-- ���Ŀ� �°� ����
select * from stud;
select * from user_tab_columns;
select * from user_tab_columns where table_name = 'STUD';
insert into stud (id, name, addr, tel) values ('aaa', '���켺', '�츮��', '1111');

-- Error ��Ȳ
insert into stud (id, name, addr, tel) values ('aaa', '������', '�ϳ���', '2222'); -- primary key ��ħ
insert into stud (name, addr, tel) values ('������', '�ϳ���', '2222'); -- primary key�� ����� ���̵� ��� Error
insert into stud (id, addr, tel) values ('bbb', '�ϳ׿���', '2222'); -- not null ������ �̸��� null �̿��� Error
insert into stud (id, name, addr, tel) values ('bbb', '������', '�ϳ���', '1111'); -- unique �� tel�� �ٸ������ ���ĵ� Error

select * from stud;

-- ������ inset 
insert into stud (id, name, addr) values ('bbb', '������', '�ϳ���'); -- tel �� unique ������ not null �� �ƴϹǷ� null �϶� ���� ����
insert into stud (id, name) values ('ccc', '������');
insert into stud (id, name, addr, tel) values ('ddd', '���ϼ�', '�ϳ���', '2222');

select * from stud;