�߰� ���� 'aa, cc, ee, ff' �л� ���� ������ ������ �ִ� view "mid_acef" �� �����ϼ���.

select * from stud;

select * from exam;

select exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg from exam
where title = '�߰�';

select * from (select exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg from exam
where title = '�߰�') t1
where sid in ('aa', 'cc', 'ee', 'ff');

create view mid_acef as 
select name, t2.* from stud, (select * from (select exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg from exam
where title = '�߰�') t1
where sid in ('aa', 'cc', 'ee', 'ff')) t2
where t2.sid = id
order by avg desc;

select * from user_views;

select * from mid_acef;

drop view mid_acef;