/*
    exam ���̺��� �����ϼ���
    �̸�, ����, ����, ����
    
    ������ ���̺��� �̿��Ͽ� ����, ����� ����ϼ���
*/

insert all
into exam (name, kor, eng, mat) values ('�Ѱ���', 97,97,97)
into exam (name, kor, eng, mat) values ('�ΰ���', 72,37,74)
into exam (name, kor, eng, mat) values ('�ﰡ��', 17,27,77)
into exam (name, kor, eng, mat) values ('�簡��', 74,47,77)
into exam (name, kor, eng, mat) values ('������', 17,65,43)
select * from dual;

delete from exam; -- �߸� �߰��Ͽ� ��ü ����Ÿ ����

select exam.*, kor + eng + mat as tot, (kor + eng + mat) / 3 as avg from exam;
--�Ѱ���	97	97	97	291	97
--�ΰ���	72	37	74	183	61
--�ﰡ��	17	27	77	121	40.33333333333333333333333333333333333333
--�簡��	74	47	77	198	66
--������	17	65	43	125	41.66666666666666666666666666666666666667