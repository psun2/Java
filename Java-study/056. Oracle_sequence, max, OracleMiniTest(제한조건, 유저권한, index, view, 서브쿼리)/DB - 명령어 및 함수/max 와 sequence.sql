-- max를 이용한 sequence의 필요성

insert into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ccc', '기말', '2019-05-05', 34, 56);

select * from vm_exam;

-- insert all
-- sequence 와 마찬가지로 id 값이 변경되지 않고 통일되어 하나로 들어 갑니다.
insert all
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '기말', '2014-05-05', 11, 11)
select * from dual;

select * from vm_exam;