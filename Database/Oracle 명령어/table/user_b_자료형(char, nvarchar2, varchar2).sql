insert into test1 (id, name, email, height, birth, regdate, hobby) values (12, 'aa', 'aaa@aaa.com', 169.9, '1990-05-12', '2020-04-28 15:30:20', '�ڵ��ϱ�');

# �ڷ���

## char 
byte�� ���� ���߾� �Է°��� �߸��ų�, ������ �� �������� ��ü�Ͽ� �� ä�� ��� ���ϴ�.

## navarchar2
nvarchar2 �� char �� varchar2 �ʹ� �޸�
byte�� �ƴ� ���ڼ��� parameter �� �޽��ϴ�.

## UTF-8 ���ڵ� ��Ŀ����� �ѱ��� 3byte�Դϴ�.

-- column �߰�
alter table test1 add(spec varchar2(20));

-- column ����
alter table test1 modify(id varchar2(40));

-- column ���� Error : ���� �����Ͱ� ��� �־� ������ �ȵ˴ϴ�. ����� �ٽ� �õ� �մϴ�.

-- delete
delete from test1;

-- column ����
alter table test1 modify(id varchar2(40));

-- ����Ȯ��
select * from user_tab_columns where table_name = 'TEST1';
-- TEST1	ID	VARCHAR2

-- column �� ����
alter table test1 rename column id to pid;

-- column ����
alter table test1 drop column spec;

-- table ����
drop table test1;

select * from tab;
