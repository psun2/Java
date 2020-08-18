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

insert into stud (id, name) values ('aa','정우성');
insert into stud (id, name) values ('bb','정남성');
insert into stud (id, name) values ('cc','정중성');
insert into stud (id, name) values ('dd','정장성');
insert into stud (id, name) values ('ee','장북건');
insert into stud (id, name) values ('ff','장서건');
insert into stud (id, name) values ('gg','장남건');
insert into stud (id, name) values ('hh','장동건');
insert into stud (id, name) values ('ii','장정건');

insert into exam(title, sid, kor, eng, mat) values ('중간','aa',77,78,71);
insert into exam(title, sid, kor, eng, mat) values ('중간','bb',67,68,61);
insert into exam(title, sid, kor, eng, mat) values ('중간','cc',87,88,81);
insert into exam(title, sid, kor, eng, mat) values ('중간','dd',97,98,91);
insert into exam(title, sid, kor, eng, mat) values ('중간','ee',57,58,51);
insert into exam(title, sid, kor, eng, mat) values ('중간','ff',74,48,41);
insert into exam(title, sid, kor, eng, mat) values ('중간','gg',73,83,13);
insert into exam(title, sid, kor, eng, mat) values ('중간','hh',75,58,51);
insert into exam(title, sid, kor, eng, mat) values ('기말','ii',17,18,11);
insert into exam(title, sid, kor, eng, mat) values ('기말','aa',71,81,11);
insert into exam(title, sid, kor, eng, mat) values ('기말','bb',57,58,51);
insert into exam(title, sid, kor, eng, mat) values ('기말','cc',27,28,12);
insert into exam(title, sid, kor, eng, mat) values ('기말','dd',73,83,13);
insert into exam(title, sid, kor, eng, mat) values ('기말','ee',37,38,31);
insert into exam(title, sid, kor, eng, mat) values ('기말','ff',87,88,81);
insert into exam(title, sid, kor, eng, mat) values ('기말','gg',47,84,41);
insert into exam(title, sid, kor, eng, mat) values ('기말','hh',17,81,11);
insert into exam(title, sid, kor, eng, mat) values ('기말','ii',57,85,51);

create view stud_exam as
select name, exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg
from stud, exam
where sid = id
order by title desc, avg desc;

select * from user_views;

drop view stud_exam;

select * from user_views;