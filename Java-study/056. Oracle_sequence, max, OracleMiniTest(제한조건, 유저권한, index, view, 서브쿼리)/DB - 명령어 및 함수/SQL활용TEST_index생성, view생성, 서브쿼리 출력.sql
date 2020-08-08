-- index ����
create index idx_exam on exam(pid);

-- index ��ȸ
select * from user_indexes;

-- view ������ ���� Ȯ��
select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by id desc;

-- view ����
create view vm_exam as
select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by id desc;

-- view Ȯ��
select * from user_views;

-- �̸��� 2������ �л� ã�� 
select * from stud
where length(name) = 2;

-- �������� ����� ���
select pid, title, kor, eng, tot, avg from vm_exam, (select * from stud
where length(name) = 2) t1
where t1.id = pid;