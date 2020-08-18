-- �ܷ�Ű (foreign key)

-- �÷��� ����
alter table stud rename column sid to id;
alter table exam rename column id to sid;

-- ����
alter table exam drop constraint exam_id_fk;

-- add
alter table exam add constraint exam_sid_fk foreign key(sid) references stud(id);

-- Ű Ȯ��
select * from user_constraints;

-- key ���� �°� ���� insert
select * from user_tab_columns where table_name = 'EXAM';
insert into exam (no, title, sid, kor, eng, mat, regdate) values (1, '�߰�', 'aaa', 77, 77, 77, '2020-08-05');
insert into exam (no, title, sid) values (2, '�߰�', 'bbb');
insert into exam (no, title) values (3, '�߰�'); -- �ܷ�Ű�� null �̿��� ������� ����

select * from exam;

-- key ���� ���� �ʰ� ������ insert
select * from user_tab_columns where table_name = 'EXAM';
select * from user_constraints where table_name = 'EXAM';
insert into exam (no, title, sid, kor, eng, mat, regdate) values (1, '�߰�', 'bbb', 77, 77, 77, '2020-08-05'); -- no�� primary key �̹Ƿ� ���� �Ұ�
insert into exam (no, title, sid, kor, eng, mat, regdate) values (4, '�߰�', 'zzz', 77, 77, 77, '2020-08-05'); -- �����ϴ� stud �� id�� zzz �� �����Ƿ� ���� �Ұ�

