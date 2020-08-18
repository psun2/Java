-- web�� �������� ��ȯ�� �������� �����ľ��� ���� rownum
-- ���� ���� ������ �Բ� ��� �ִ� ������ ���̺��� view �� �Բ� ��� �˴ϴ�.

select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by regdate desc; -- ������ ������ �������� �������� rownum �� �ٿ� �־�� �մϴ�.

select rownum rnum, t1.* from (select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by regdate desc) t1;

-- rownum�� ���� �ޱ�
select rownum rnum, t1.* from (select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by regdate desc) t1 
where rownum >=1 and rownum <= 5;

-- view �� �����
create view res_exam as
select rownum rnum, t1.* from (select exam.*, kor + eng as tot, (kor + eng) / 2 as avg from exam
order by regdate desc) t1;

select * from user_views;

select * from res_exam;

-- view �� Ȱ���Ͽ� ������� �������̺� rownum ���� �ޱ�
select * from res_exam
where rownum >=1 and rownum <= 5;

-- ��������ִ� exam_id sequence �� ������� res_exam view �� Ȱ���� insert
insert into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'bbb', '�⸻', '1995-05-05', 100, 100);

select * from res_exam;