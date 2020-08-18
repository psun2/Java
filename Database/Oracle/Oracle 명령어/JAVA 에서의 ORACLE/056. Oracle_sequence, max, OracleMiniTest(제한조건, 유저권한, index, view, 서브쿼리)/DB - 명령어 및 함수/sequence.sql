select * from user_views;

-- sequence ����
create sequence exam_id
start with 100 -- ���� ��ȣ
increment by 1; 

-- sequence ��ȸ
select * from user_sequences;

-- sequence ����
drop sequence exam_id;

select * from exam;
select * from VM_EXAM;

-- exam�� view �� �����ְ�, exam �� pid�� stud�� �����ϰ� �־,
-- view �� �����ϱ� ������ insert �Ұ�
select * from stud;
insert into stud (id, name) values ('zzz', '����');
select * from user_constraints;
-----------------------------------------------------------

-- sequence �� id�� ����
insert into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'aaa', '�⸻', '2019-05-05', 34, 56);

-- ������ sequence �� ����
select exam_id.currval from dual;

-- sequence �� ������ ���̺��� �� ����
select * from vm_exam;

-- sequence ��ü(����)
-- start with �� ��ü, ���� �Ұ�
-- ����� �ٽ� �缳���Ͽ� ������ �մϴ�.
alter sequence exam_id
increment by 2;

select * from user_sequences;

drop sequence exam_id;

create sequence exam_id
start with 400
increment by 10;

-- insert all ���� sequence
-- id ���� ������� �ʰ� ���ϵǾ� �ϳ��� ��� ���ϴ�.
insert all
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '�⸻', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '�⸻', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '�⸻', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '�⸻', '2019-05-05', 34, 56)
into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, 'eee', '�⸻', '2019-05-05', 34, 56)
select * from dual;

select * from vm_exam;