-- 예제를 위한 테이블과 데이터 생성
create table car(
name varchar2(20),
price number(8),
type varchar2(20)
);

insert all
into car (name, price, type) values ('람보르기니', 1000, '트럭')
into car (name, price, type) values ('페라리', 4000, '비행기')
into car (name, price, type) values ('코라리', 1400, '승용차')
into car (name, price, type) values ('워라리', 1500, '버스')
into car (name, price, type) values ('람보르걷니', 2000, '버스')
into car (name, price, type) values ('람보르뚜니', 2300, '승용차')
into car (name, price, type) values ('람보르나니', 3000, '비행기')
into car (name, price, type) values ('간라리', 1600, '트럭')
into car (name, price, type) values ('팔라리', 2000, '버스')
into car (name, price, type) values ('손라리', 5000, '승용차')
select * from dual;

select * from car;

-- ▶Index 조회
select * from user_indexes;

-- ▶Index 생성
create index index_name on 테이블명(해당컬럼명);
create index car_name_idx on car(name);

select * from user_indexes where table_name = 'CAR';
--CAR_NAME_IDX

-- ▶Index 이름 변경
alter index car_name_idx rename to idx_name_car;

select * from user_indexes where table_name = 'CAR';
--IDX_NAME_CAR

-- ▶Index 삭제
drop index idx_name_car;

select * from user_indexes where table_name = 'CAR';

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
stud의 name, tel 로 idx_stud 인덱스를 생성하세요(pk 제거)

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