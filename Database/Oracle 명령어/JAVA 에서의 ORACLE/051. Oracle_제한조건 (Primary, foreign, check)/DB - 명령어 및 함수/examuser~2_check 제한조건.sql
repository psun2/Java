-- check ���� ����
-- age number constraint test2_age_chk check (age between 0 and 150);
-- alter table test3 add constraint t3_chk_kor check (kor between 0 and 150);
-- alter table test3 add constraint t3_chk_kor check (kor >= 0 and kor <= 150);
-- alter table test3 add constraint t3_chk_kor check (ban in(1, 3)); -- ���� 1�� �Ǵ� 3�� ���� �ްڴ�.

alter table exam add constraint exam_title_chk check (title in('�߰�', '�⸻'));

-- ŰȮ��
select * from user_constraints where table_name = 'EXAM';

insert into exam (no, title, sid, kor, eng, mat, regdate) values (10, '�߰����', 'bbb', 77, 77, 77, '2020-08-05'); -- title �߰� �Ǵ� �⸻�� �ƴϿ��� Error

insert into exam (no, title, sid, kor, eng, mat, regdate) values (10, '�⸻���', 'bbb', 77, 77, 77, '2020-08-05'); -- title �߰� �Ǵ� �⸻�� �ƴϿ��� Error