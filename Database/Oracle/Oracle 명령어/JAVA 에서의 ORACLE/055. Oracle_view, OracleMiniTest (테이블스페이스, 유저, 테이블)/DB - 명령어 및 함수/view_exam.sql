중간 고사와 'aa, cc, ee, ff' 학생 시험 정보를 가지고 있는 view "mid_acef" 를 생성하세요.

select * from stud;

select * from exam;

select exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg from exam
where title = '중간';

select * from (select exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg from exam
where title = '중간') t1
where sid in ('aa', 'cc', 'ee', 'ff');

create view mid_acef as 
select name, t2.* from stud, (select * from (select exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg from exam
where title = '중간') t1
where sid in ('aa', 'cc', 'ee', 'ff')) t2
where t2.sid = id
order by avg desc;

select * from user_views;

select * from mid_acef;

drop view mid_acef;