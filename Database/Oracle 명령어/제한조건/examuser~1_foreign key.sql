-- 외래키 (foreign key)

-- 컬럼명 변경
alter table stud rename column sid to id;
alter table exam rename column id to sid;

-- 삭제
alter table exam drop constraint exam_id_fk;

-- add
alter table exam add constraint exam_sid_fk foreign key(sid) references stud(id);

-- 키 확인
select * from user_constraints;

-- key 값에 맞게 정상 insert
select * from user_tab_columns where table_name = 'EXAM';
insert into exam (no, title, sid, kor, eng, mat, regdate) values (1, '중간', 'aaa', 77, 77, 77, '2020-08-05');
insert into exam (no, title, sid) values (2, '중간', 'bbb');
insert into exam (no, title) values (3, '중간'); -- 외래키는 null 이여도 상관없이 삽입

select * from exam;

-- key 값에 맞지 않게 비정상 insert
select * from user_tab_columns where table_name = 'EXAM';
select * from user_constraints where table_name = 'EXAM';
insert into exam (no, title, sid, kor, eng, mat, regdate) values (1, '중간', 'bbb', 77, 77, 77, '2020-08-05'); -- no가 primary key 이므로 삽입 불가
insert into exam (no, title, sid, kor, eng, mat, regdate) values (4, '중간', 'zzz', 77, 77, 77, '2020-08-05'); -- 참조하는 stud 의 id에 zzz 가 없으므로 삽입 불가

