-- check 제한 조건
-- age number constraint test2_age_chk check (age between 0 and 150);
-- alter table test3 add constraint t3_chk_kor check (kor between 0 and 150);
-- alter table test3 add constraint t3_chk_kor check (kor >= 0 and kor <= 150);
-- alter table test3 add constraint t3_chk_kor check (ban in(1, 3)); -- 반을 1반 또는 3반 만을 받겠다.

alter table exam add constraint exam_title_chk check (title in('중간', '기말'));

-- 키확인
select * from user_constraints where table_name = 'EXAM';

insert into exam (no, title, sid, kor, eng, mat, regdate) values (10, '중간고사', 'bbb', 77, 77, 77, '2020-08-05'); -- title 중간 또는 기말이 아니여서 Error

insert into exam (no, title, sid, kor, eng, mat, regdate) values (10, '기말고사', 'bbb', 77, 77, 77, '2020-08-05'); -- title 중간 또는 기말이 아니여서 Error