select * from user_views;

-- sequence 생성
create sequence exam_id
start with 100 -- 시작 번호
increment by 1; 

-- sequence 조회
select * from user_sequences;

-- sequence 삭제
drop sequence exam_id;

select * from exam;
select * from VM_EXAM;

-- exam이 view 로 잡혀있고, exam 의 pid가 stud를 참조하고 있어서,
-- view 를 삭제하기 이전에 insert 불가
select * from stud;
insert into stud (id, name) values ('zzz', '빈빈빈');
select * from user_constraints;
-----------------------------------------------------------

-- sequence 로 id값 증가
insert into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'aaa', '기말', '2019-05-05', 34, 56);

-- 현재의 sequence 값 보기
select exam_id.currval from dual;

-- sequence 로 증가한 테이블의 값 보기
select * from vm_exam;

-- sequence 교체(수정)
-- start with 는 교체, 수정 불가
-- 지운뒤 다시 재설정하여 만들어야 합니다.
alter sequence exam_id
increment by 2;

select * from user_sequences;

drop sequence exam_id;

create sequence exam_id
start with 400
increment by 10;

-- insert all 사용시 sequence
-- id 값이 변경되지 않고 통일되어 하나로 들어 갑니다.
insert all
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '기말', '2019-05-05', 34, 56)
select * from dual;

select * from vm_exam;