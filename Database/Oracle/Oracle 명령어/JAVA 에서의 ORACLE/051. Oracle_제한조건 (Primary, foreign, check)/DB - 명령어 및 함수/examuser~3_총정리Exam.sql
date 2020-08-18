학과 테이블 : id(pk), 학과명

교수 테이블 : id(pk), 교수명(not null), 학과(fk), 전화번호(unique)

stud : 교수 컬럼 추가(fk)

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