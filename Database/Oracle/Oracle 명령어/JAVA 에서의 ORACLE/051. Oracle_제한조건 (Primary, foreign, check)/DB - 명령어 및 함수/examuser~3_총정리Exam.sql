�а� ���̺� : id(pk), �а���

���� ���̺� : id(pk), ������(not null), �а�(fk), ��ȭ��ȣ(unique)

stud : ���� �÷� �߰�(fk)

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

create table department(
id varchar2(20) constraint dpm_id_pk primary key,
name varchar2(20)
);

create table professor(
id varchar2(20) constraint pro_id_pk primary key,
name varchar2(20) constraint pro_name_nn not null,
department varchar2(50) constraint pro_dpm_fk references department(id),
tel varchar2(50) constraint pro_tel_uni unique
);

alter table stud add (professor varchar2(20) constraint stud_pro_fk references professor(id));

select * from user_constraints;
select * from user_constraints where table_name = 'DEPARTMENT';
select * from user_constraints where table_name = 'PROFESSOR';
select * from user_constraints where table_name = 'STUD';

select * from stud;