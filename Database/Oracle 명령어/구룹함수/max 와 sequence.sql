-- max�� �̿��� sequence�� �ʿ伺

insert into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ccc', '�⸻', '2019-05-05', 34, 56);

select * from vm_exam;

-- insert all
-- sequence �� ���������� id ���� ������� �ʰ� ���ϵǾ� �ϳ��� ��� ���ϴ�.
insert all
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '�⸻', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '�⸻', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '�⸻', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '�⸻', '2014-05-05', 11, 11)
into exam (id, pid, title, regdate, kor, eng) values ((select max(id) + 1 from exam), 'ddd', '�⸻', '2014-05-05', 11, 11)
select * from dual;

select * from vm_exam;