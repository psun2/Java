create table stud(
id varchar2(20),
name varchar2(20)
);

create table exam(
title varchar2(30),
sid varchar2(10),
kor int,
eng int,
mat int
);

insert into stud (id, name) values ('aa','���켺');
insert into stud (id, name) values ('bb','������');
insert into stud (id, name) values ('cc','���߼�');
insert into stud (id, name) values ('dd','���强');
insert into stud (id, name) values ('ee','��ϰ�');
insert into stud (id, name) values ('ff','�弭��');
insert into stud (id, name) values ('gg','�峲��');
insert into stud (id, name) values ('hh','�嵿��');
insert into stud (id, name) values ('ii','������');

insert into exam(title, sid, kor, eng, mat) values ('�߰�','aa',77,78,71);
insert into exam(title, sid, kor, eng, mat) values ('�߰�','bb',67,68,61);
insert into exam(title, sid, kor, eng, mat) values ('�߰�','cc',87,88,81);
insert into exam(title, sid, kor, eng, mat) values ('�߰�','dd',97,98,91);
insert into exam(title, sid, kor, eng, mat) values ('�߰�','ee',57,58,51);
insert into exam(title, sid, kor, eng, mat) values ('�߰�','ff',74,48,41);
insert into exam(title, sid, kor, eng, mat) values ('�߰�','gg',73,83,13);
insert into exam(title, sid, kor, eng, mat) values ('�߰�','hh',75,58,51);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','ii',17,18,11);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','aa',71,81,11);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','bb',57,58,51);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','cc',27,28,12);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','dd',73,83,13);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','ee',37,38,31);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','ff',87,88,81);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','gg',47,84,41);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','hh',17,81,11);
insert into exam(title, sid, kor, eng, mat) values ('�⸻','ii',57,85,51);

create view stud_exam as
select name, exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg
from stud, exam
where sid = id
order by title desc, avg desc;

select * from user_views;

drop view stud_exam;

select * from user_views;