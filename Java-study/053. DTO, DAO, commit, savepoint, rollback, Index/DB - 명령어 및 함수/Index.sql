-- ������ ���� ���̺�� ������ ����
create table car(
name varchar2(20),
price number(8),
type varchar2(20)
);

insert all
into car (name, price, type) values ('���������', 1000, 'Ʈ��')
into car (name, price, type) values ('���', 4000, '�����')
into car (name, price, type) values ('�ڶ�', 1400, '�¿���')
into car (name, price, type) values ('����', 1500, '����')
into car (name, price, type) values ('�������ȴ�', 2000, '����')
into car (name, price, type) values ('�������Ѵ�', 2300, '�¿���')
into car (name, price, type) values ('����������', 3000, '�����')
into car (name, price, type) values ('����', 1600, 'Ʈ��')
into car (name, price, type) values ('�ȶ�', 2000, '����')
into car (name, price, type) values ('�ն�', 5000, '�¿���')
select * from dual;

select * from car;

-- ��Index ��ȸ
select * from user_indexes;

-- ��Index ����
create index index_name on ���̺��(�ش��÷���);
create index car_name_idx on car(name);

select * from user_indexes where table_name = 'CAR';
--CAR_NAME_IDX

-- ��Index �̸� ����
alter index car_name_idx rename to idx_name_car;

select * from user_indexes where table_name = 'CAR';
--IDX_NAME_CAR

-- ��Index ����
drop index idx_name_car;

select * from user_indexes where table_name = 'CAR';

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
stud�� name, tel �� idx_stud �ε����� �����ϼ���(pk ����)

select * from user_constraints where table_name = 'STUD';
--STUD_ID_PK
--STUD_NAME_NN

select * from user_constraints where r_constraint_name = 'STUD_ID_PK';
--EXAM_SID_FK

alter table exam drop constraint exam_sid_fk;
alter table stud drop constraint stud_id_pk;

create index stud_id_idx on stud(id);
create index stud_name_idx on stud(name);

select * from user_indexes where table_name = 'STUD';